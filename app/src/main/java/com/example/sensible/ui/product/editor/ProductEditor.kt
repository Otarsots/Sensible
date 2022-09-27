package com.example.sensible.ui.product.editor

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.sensible.R
import com.example.sensible.ui.components.AnimatedCircle
import com.example.sensible.ui.components.SensibleTopBar
import com.example.sensible.ui.theme.extendedColors
import okhttp3.internal.toLongOrDefault
import org.koin.androidx.compose.getViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun ProductEditor(
    productId: Long,
    recipeId: Long,
    popBackStack: () -> Unit,
    viewModel: ProductEditorViewModel = getViewModel { parametersOf(Pair(recipeId,productId)) }
) {
    val name by viewModel.name.collectAsState()
    val image by viewModel.image.collectAsState()
    val calories by viewModel.calories.collectAsState()
    val carbs by viewModel.carbs.collectAsState()
    val fat by viewModel.fat.collectAsState()
    val protein by viewModel.protein.collectAsState()

    Scaffold(
        topBar = {
            SensibleTopBar(
                navigationIcon = {
                    IconButton(onClick = popBackStack) {
                        Icon(Icons.Default.ArrowBack, stringResource(R.string.btn_pop_back))
                    }
                },
                title = stringResource(R.string.product_editor_name),
                actions = {
                        Image(
                            painterResource(id = R.drawable.ic_action_check),
                            contentDescription ="",
                            modifier = Modifier
                                .padding(2.dp)
                                .size(28.dp)
                                .clickable {
                                    viewModel.save(popBackStack)
                                },
                        colorFilter = ColorFilter.tint(MaterialTheme.colors.onSurface))
                }
            )
        }
    ) { padding ->
        Surface(
            modifier = Modifier
                .padding(padding)
                .padding(4.dp),
            color = MaterialTheme.colors.primary,
            shape = RoundedCornerShape(8.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {


                Text(
                    text = name,
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier
                        .padding(horizontal = 8.dp, vertical = 8.dp),
                )
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Top
                    ) {
                        Spacer(modifier = Modifier.size(48.dp))
                        AsyncImage(
                            modifier = Modifier
                                .size(240.dp)
                                .clip(shape = RoundedCornerShape(8.dp))
                                .border(
                                    width = 4.dp,
                                    color = MaterialTheme.colors.primary,
                                    shape = RoundedCornerShape(8.dp)
                                ),
                            model = image,
                            contentScale = ContentScale.Crop,
                            contentDescription = null,
                        )
                        val amount by viewModel.amount.collectAsState()
                        OutlinedTextField(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 16.dp, end = 16.dp, top = 16.dp),
                            value = "$amount",
                            onValueChange = { viewModel.setAmount(it.toLongOrDefault(0)) },
                            label = { Text("Amount in grams") },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
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
                                    text = String.format("Calories: %.1fkcal",calories),
                                    style = MaterialTheme.typography.body2,
                                )
                                Text(
                                    text = String.format("Carbs: %.1fg",carbs),
                                    style = MaterialTheme.typography.body2,
                                    color = MaterialTheme.extendedColors.carbs
                                )
                                Text(
                                    text = String.format("Fat: %.1fg",fat),
                                    style = MaterialTheme.typography.body2,
                                    color = MaterialTheme.extendedColors.fat
                                )
                                Text(
                                    text = String.format("Protein: %.1fg",protein),
                                    style = MaterialTheme.typography.body2,
                                    color = MaterialTheme.extendedColors.protein
                                )
                            }
                        }

                    }
                }
            }

        }
    }
    }
