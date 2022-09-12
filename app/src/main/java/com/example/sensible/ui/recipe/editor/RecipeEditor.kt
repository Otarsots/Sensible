package com.example.sensible.ui.recipe.editor

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.example.sensible.R
import com.example.sensible.models.Ingredient
import com.example.sensible.models.Recipe
import com.example.sensible.ui.components.*
import com.example.sensible.ui.theme.extendedColors
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanOptions
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun RecipeEditor(
    recipeId: Long,
    popBackStack: () -> Unit = {},
    navToProduct: (Long) -> Unit = {},
    viewModel: RecipeEditorViewModel = getViewModel { parametersOf(recipeId) }
) {
    val scanLauncher = rememberLauncherForActivityResult(
        contract = ScanContract(),
        onResult = { result ->
            if (result.contents != null) {
                val id = result.contents.toLong()
                viewModel.getProduct(id)
                viewModel.addProduct(id, 100) {
                    navToProduct(id)
                }
            }
        }
    )
    Scaffold(
        topBar = {
            SensibleTopBar(
                navigationIcon = {
                    IconButton(onClick = popBackStack) {
                        Icon(Icons.Default.ArrowBack, stringResource(R.string.btn_pop_back))
                    }
                },
                title = stringResource(R.string.recipes_editor_name),
            )
        },
        floatingActionButton = {
            SensibleActionButton (
                onClick = {try{
                    scanLauncher.launch(ScanOptions()
                    .setBeepEnabled(false)
                    .setDesiredBarcodeFormats(ScanOptions.PRODUCT_CODE_TYPES)
                    .setPrompt("Scan your product")
                    )}catch (e:Exception){

                    }}
            )
        }
    ) { paddingValues ->
            RecipeEditorContent(
                navToProduct = navToProduct,
                viewModel = viewModel,
                modifier = Modifier.padding(paddingValues))
        }
    }

@OptIn(ExperimentalMaterialApi::class, ExperimentalFoundationApi::class)
@Composable
fun RecipeEditorContent(
    navToProduct: (Long) -> Unit,
    viewModel: RecipeEditorViewModel,
    modifier: Modifier,
) {
    val name by viewModel.name.collectAsState()
    val nutrients by viewModel.nutrients.collectAsState()
    val ingredients by viewModel.ingredients.collectAsState()

    Column {
        LaunchedEffect(name) {
            viewModel.updateName(name)
        }
        Surface(
            shape = RoundedCornerShape(8.dp),
            //color = MaterialTheme.colors.primary,
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth()
        ) {
            Column {
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp, start = 30.dp, end = 30.dp),
                    value = name,
                    label = { Text("Name") },
                    onValueChange = { viewModel.setName(it) },
                    singleLine = true,
                )
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    //verticalAlignment = Alignment.Top
                )
                {
                    AnimatedCircle(
                        modifier = Modifier
                            .size(200.dp)
                            .padding(16.dp),
                        proportions = viewModel.getProportions(),
                        colors = listOf(
                            MaterialTheme.extendedColors.carbs,
                            MaterialTheme.extendedColors.fat,
                            MaterialTheme.extendedColors.protein
                        )
                    )


                    Column(
                        modifier = Modifier
                            .padding(16.dp)
                            .size(150.dp),
                        verticalArrangement = Arrangement.Center
                    ) {

                        Text(
                            text = "Calories: ${nutrients.calories}kcal",
                            style = MaterialTheme.typography.body2,
                        )
                        Text(
                            text = "Carbohydrates: ${nutrients.carbs}g",
                            style = MaterialTheme.typography.body2,
                            color = MaterialTheme.extendedColors.carbs
                        )
                        Text(
                            text = "Fat: ${nutrients.fat}g",
                            style = MaterialTheme.typography.body2,
                            color = MaterialTheme.extendedColors.fat
                        )
                        Text(
                            text = "Protein: ${nutrients.protein}g",
                            style = MaterialTheme.typography.body2,
                            color = MaterialTheme.extendedColors.protein
                        )
                    }
                }
            }
        }
            Surface(
                shape = RoundedCornerShape(8.dp),
                color = MaterialTheme.colors.primary,
                modifier = Modifier
                    .padding(4.dp)
                    .fillMaxWidth()
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "Ingredients:",
                        style = MaterialTheme.typography.h6,
                        modifier = Modifier
                            .padding(horizontal = 8.dp, vertical = 8.dp),
                    )
                    Surface(

                    ) {

                        LazyColumn(
                            Modifier
                                .fillMaxSize(),
                                //.padding(4.dp),
                            contentPadding = PaddingValues(
                                top = 4.dp,
                                bottom = 70.dp),
                        ) {
                            items(
                                items = ingredients,
                                key = { it.product.productId }) { ingredient ->
                                val product = ingredient.product
                                val dismissState = rememberDismissState()

                                SwipeToDismiss(
                                    modifier = Modifier
                                        .animateItemPlacement()
                                        .zIndex(if (dismissState.offset.value == 0f) 0f else 1f),
                                    state = dismissState,
                                    directions = setOf(DismissDirection.StartToEnd),
                                    dismissThresholds = { FractionalThreshold(0.7f) },
                                    background = { SwipeToDeleteBackground(dismissState) }
                                ) {
                                    FoodListItem(
                                        onItemClick = { navToProduct(product.productId) },
                                        title = product.productName.takeIf { it.isNotBlank() }
                                            ?: "TODO",
                                        image = product.imageUrl,
                                        amount = ingredient.amount,
                                        calories = product.energyKcal100g * ingredient.amount / 100
                                    )
                                }

                                if (dismissState.targetValue != DismissValue.Default) {
                                    viewModel.removeIngredient(product.productId)
                                }
                            }
                        }
                    }
                }
            }
    }
}