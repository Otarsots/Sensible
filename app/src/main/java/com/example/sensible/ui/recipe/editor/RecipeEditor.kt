package com.example.sensible.ui.recipe.editor

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.sensible.ui.components.SensibleTopBar
import org.koin.androidx.compose.getViewModel
import org.koin.core.parameter.parametersOf
import com.example.sensible.R
import com.example.sensible.models.Product
import com.example.sensible.models.Recipe
import kotlinx.coroutines.launch
import kotlinx.coroutines.yield

@Composable
fun RecipeEditor(
    recipeId: Long,
    popBackStack: () -> Unit = {},
    viewModel: RecipeEditorViewModel = getViewModel { parametersOf(recipeId) }
) {
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
        }
    ) { paddingValues ->
        val recipe by viewModel.recipe.collectAsState(initial = null)
            recipe?.let { recipe ->
                RecipeEditorContent(
                    recipe = recipe.recipe,
                    ingredients = recipe.productList,
                    viewModel = viewModel,
                    modifier = Modifier.padding(paddingValues))
            }
        }
    }

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun RecipeEditorContent(
    recipe: Recipe,
    ingredients: List<Product>,
    viewModel: RecipeEditorViewModel,
    modifier: Modifier,
) {
    val scope = rememberCoroutineScope()
    LazyColumn(
        Modifier.fillMaxHeight(),
        contentPadding = PaddingValues(bottom = 70.dp)
    ) {
        item {
            val (name, setName) = remember { mutableStateOf(recipe.name) }
            LaunchedEffect(name) {
                viewModel.updateName(name)
            }
            val (nameLineCount, setNameLineCount) = remember { mutableStateOf(0) }
            BasicTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, start = 30.dp, end = 30.dp),
                value = name,
                onValueChange = setName
            )
        }
        items(items = ingredients, key = {it.productId}) { product ->
            ListItem(
                Modifier.clickable(
                    onClick = {}//TODO navigation to product editor
                ),
                text = {
                    Text(
                        text = product.productName.takeIf { it.isNotBlank() }
                            ?: "TODO",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                },
                trailing = {
                    Box {
                        var expanded by remember { mutableStateOf(false) }
                        IconButton(onClick = { expanded = !expanded }) {
                            Icon(Icons.Default.MoreVert, null)
                        }
                        DropdownMenu(
                            expanded = expanded,
                            onDismissRequest = { expanded = false }
                        ) {
                            DropdownMenuItem(
                                onClick = {
                                    expanded = false
                                    scope.launch {}
                                }
                            ) {
                                Text("")
                            }
                        }
                    }
                }
            )
        }
    }
}