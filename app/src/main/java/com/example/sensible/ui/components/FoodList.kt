package com.example.sensible.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.sensible.models.Product
import com.example.sensible.models.Recipe
import com.example.sensible.ui.theme.SensibleTheme
import java.util.*
import kotlin.collections.ArrayList
/*
@Composable
fun FoodList(
    onItemClick: (String) -> Unit = {},
    state: MutableState<TextFieldValue>,

    foods: List<Product>
) {
        var filteredFoods: List<Product>
        LazyColumn(
            contentPadding = PaddingValues(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            val searchedText = state.value.text
            filteredFoods = if (searchedText.isBlank()) {
                foods
            } else {
                val resultList = ArrayList<Product>()
                for (food in foods) {
                    if (food.productName
                            .lowercase(Locale.getDefault())
                            .contains(
                                searchedText.lowercase(Locale.getDefault())
                            )
                    ) {
                        resultList.add(food)
                    }
                }
                resultList
            }
            items(filteredFoods) { food ->
                Box(Modifier.padding(2.dp)) {
                    FoodListItem(
                        title = food.productName,
                        image = rememberAsyncImagePainter(food.imageURL),
                        calories = food.caloriesKcal100g,
                        amount = food.amount,
                        onItemClick = onItemClick
                    )
                }
            }
        }
}

@Composable
fun SearchableFoodList(
    onItemClick: (String) -> Unit,
    foods: List<FoodInterface>
){
    val textState = remember { mutableStateOf(TextFieldValue("")) }
    Surface(
        elevation = 2.dp,
        color = MaterialTheme.colors.surface,
        modifier = Modifier.fillMaxSize()
    ) {
        Column {
            SearchBar(
                textState, color = MaterialTheme.colors.surface,
                modifier = Modifier.padding(8.dp)
            ) {}
            FoodList(
                onItemClick = {},
                state = textState,
                foods = foods
            )
        }
    }
}

@Preview
@Composable
fun searchableFoodListPreview(){
    SensibleTheme() {
        val foods = remember{ ArrayList<Recipe>() }
        foods.add(Recipe(emptyList(),100,1.0,"Griechische Reispfanne mit Spargel und ganz viel anderem Zeug"))
        foods.add(Recipe(emptyList(),100,0.5,"Spargel"))
        foods.add(Recipe(emptyList(),100,0.5,"Spargel"))
        foods.add(Recipe(emptyList(),100,0.5,"Spargel"))
        foods.add(Recipe(emptyList(),100,0.5,"Spargel"))
        foods.add(Recipe(emptyList(),100,0.5,"Spargel"))
        foods.add(Recipe(emptyList(),100,0.5,"Spargel"))
        foods.add(Recipe(emptyList(),100,0.5,"Spargel"))
        foods.add(Recipe(emptyList(),100,0.5,"Spargel"))
        foods.add(Recipe(emptyList(),100,0.5,"Spargel"))
        foods.add(Recipe(emptyList(),100,0.5,"irgendwas mit Ei"))
        SearchableFoodList(
            onItemClick = {},
            foods = foods,)
    }
}*/