package com.example.sensible.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.sensible.models.DiaryEntry
import com.example.sensible.models.FoodInterface
import com.example.sensible.models.Recipe
import com.example.sensible.ui.theme.SensibleTheme
import java.util.*
import kotlin.collections.ArrayList

@Composable
fun FoodList(
    onItemClick: (String) -> Unit = {},
    state: MutableState<TextFieldValue>,

    foods: List<FoodInterface>
) {
    Surface(
        elevation = 2.dp,
        color = MaterialTheme.colors.surface,
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        var filteredFoods: List<FoodInterface>
        LazyColumn(
            contentPadding = PaddingValues(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            val searchedText = state.value.text
            filteredFoods = if (searchedText.isBlank()) {
                foods
            } else {
                val resultList = ArrayList<FoodInterface>()
                for (food in foods) {
                    if (food.name
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
                        title = food.name,
                        image = rememberAsyncImagePainter(food.imageURL),
                        calories = food.calories,
                        amount = food.amount,
                        onItemClick = onItemClick
                    )
                }
            }
        }
    }
}

@Composable
fun searchableFoodList(
    onItemClick: (String) -> Unit,
    foods: List<FoodInterface>
){
    val textState = remember { mutableStateOf(TextFieldValue("")) }
    Column {
        SearchBar(textState) {}
        FoodList(
            onItemClick = {},
            state = textState,
            foods = foods
        )
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
        searchableFoodList(onItemClick = {},
            foods = foods)
    }
}