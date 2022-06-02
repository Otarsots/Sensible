package com.example.sensible.ui.diary

import androidx.compose.material.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.sensible.models.DiaryEntry
import com.example.sensible.models.Recipe
import com.example.sensible.ui.components.FoodListItem
import com.example.sensible.ui.theme.SensibleTheme


@Composable
fun DiaryBody(
    entries: List<DiaryEntry>,
    //onEntryClick: (String) -> Unit = {},
    //onAddClick: (String) -> Unit = {}
){
}


@Composable
fun EntryList(
    entry: DiaryEntry
) {
    Surface (
        elevation = 2.dp,
        color = MaterialTheme.colors.surface,
        shape = RoundedCornerShape(8.dp)){
        LazyColumn(contentPadding = PaddingValues(8.dp)) {
            items(entry.foods) { food ->
                Box(Modifier.padding(2.dp)) {
                    FoodListItem(
                        title = food.name,
                        image = rememberAsyncImagePainter(food.imageURL),
                        calories = food.calories,
                        amount = food.amount,
                    )
                }
            }
        }
    }
}
/*
@Composable
fun DiaryScreen() {
    ScreenSetup()
}

@Composable
fun TitleRow(head1: String, head2: String, head3: String) {
    Row(
        modifier = Modifier
            .background(MaterialTheme.colors.primary)
            .fillMaxWidth()
            .padding(5.dp)
    ) {
        Text(head1, color = Color.White,
            modifier = Modifier
                .weight(0.1f))
        Text(head2, color = Color.White,
            modifier = Modifier
                .weight(0.2f))
        Text(head3, color = Color.White,
            modifier = Modifier.weight(0.2f))
    }
}

@Composable
fun ProductRow(id: Int, name: String, quantity: Int) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)F
    ) {
        Text(id.toString(), modifier = Modifier
            .weight(0.1f))
        Text(name, modifier = Modifier.weight(0.2f))
        Text(quantity.toString(), modifier = Modifier.weight(0.2f))
    }
}

@Composable
fun CustomTextField(
    title: String,
    textState: String,
    onTextChange: (String) -> Unit,
    keyboardType: KeyboardType
) {
    OutlinedTextField(
        value = textState,
        onValueChange = { onTextChange(it) },
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType
        ),
        singleLine = true,
        label = { Text(title)},
        modifier = Modifier.padding(10.dp),
        textStyle = TextStyle(fontWeight = FontWeight.Bold,
            fontSize = 30.sp)
    )
}

@Composable
fun ScreenSetup(
    viewModel: MainViewModel =
        MainViewModel(LocalContext.current.applicationContext as Application)
) {
    val allFoods by viewModel.allProducts.observeAsState(listOf())
    val searchResults by viewModel.searchResults.observeAsState(listOf())

    MainScreen(
        allProducts = allFoods,
        searchResults = searchResults,
        viewModel = viewModel
    )
}

@Composable
fun MainScreen(
    allProducts: List<Product>,
    searchResults: List<Product>,
    viewModel: MainViewModel
) {

    var coroutineScope = rememberCoroutineScope()
    val textState = remember { mutableStateOf(TextFieldValue(text="3017620422003")) }
    val addProductOnClick: () -> Unit = {
        coroutineScope.launch {
            viewModel.insertProduct(textState.value.text.toLong())
        }
    }
    Column {
        Row {
            TextField(
                value = textState.value,
                onValueChange = { textState.value = it },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
            Button(onClick = addProductOnClick) {

            }
        }
        LazyColumn(
            Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            items(allProducts) { product ->
                Column {
                    Text(product.productName)
                    Text("Protein: " + product.nutriments.proteins100g.toString() + product.nutriments.proteinsUnit)
                }

            }
        }
    }

}*/

@Preview
@Composable
fun FoodListPreview(){
    SensibleTheme(darkTheme = true){
        val entry = remember{DiaryEntry(1, mutableListOf())}
        entry.foods.add(Recipe(emptyList(),100,1.0,"Griechische Reispfanne mit Spargel und ganz viel anderem Zeug"))
        entry.foods.add(Recipe(emptyList(),100,0.5,"Spargel"))
        EntryList(entry = entry)
    }
}

@Preview
@Composable
fun LightFoodListPreview(){
    SensibleTheme(darkTheme = false){
        val entry = remember{DiaryEntry(1, mutableListOf())}
        entry.foods.add(Recipe(emptyList(),100,1.0,"Griechische Reispfanne mit Spargel und ganz viel anderem Zeug"))
        entry.foods.add(Recipe(emptyList(),100,0.5,"Spargel"))
        EntryList(entry = entry)
    }
}