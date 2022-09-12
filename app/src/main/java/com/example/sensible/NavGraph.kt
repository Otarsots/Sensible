package com.example.sensible

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.sensible.ui.diary.editor.DiaryEditor
import com.example.sensible.ui.diary.search.RecipeSearch
import com.example.sensible.ui.home.HomeBody
import com.example.sensible.ui.product.editor.ProductEditor
import com.example.sensible.ui.recipe.editor.RecipeEditor
import com.example.sensible.ui.recipe.list.RecipeList
import java.time.LocalDate

@Suppress("EnumEntryName")
enum class Screen {
    home,
    diaryEditor,
    diarySearch,
    recipeList,
    recipeEditor,
    recipeSearch,
    productEditor,
    barcodeScanner,
    settings,
    goals
}

@Composable
fun SensibleNavHost(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(
        navController = navController,
        startDestination = Screen.home.name,
        modifier = modifier
    ) {

        composable(Screen.home.name) {
            HomeBody()
        }
        composable(
            route = "${Screen.diaryEditor.name}?date={date}",
            arguments = listOf(
                navArgument("date"){
                    defaultValue = LocalDate.now().toEpochDay()
                    type = NavType.LongType
                })){ backStackEntry ->
            DiaryEditor(
                date = backStackEntry.arguments!!.getLong("date"),
                entries = emptyList(),
                navToRecipeEditor = {recipeId -> navController.navigate("${Screen.recipeEditor.name}?recipeId=$recipeId")},
                onAddClick = {navController.navigate(Screen.recipeSearch.name)},
                navToDate = {date -> navController.navigate("${Screen.diaryEditor.name}?date=$date")}
            )
        }
        composable(Screen.recipeSearch.name){
            RecipeSearch(
                popBackStack = {navController.popBackStack()},
                onScanClick = {navController.navigate(Screen.barcodeScanner.name)}
            )
        }
        composable(Screen.recipeList.name){
            RecipeList(
                navToRecipeEditor = {recipeId -> navController.navigate("${Screen.recipeEditor.name}?recipeId=$recipeId")},
            )
        }
        composable(
            route = "${Screen.recipeEditor.name}?recipeId={recipeId}",
            arguments = listOf(
                navArgument("recipeId"){
                    defaultValue = -1
                    type = NavType.LongType
                })){ backStackEntry ->
            val id = backStackEntry.arguments!!.getLong("recipeId")
            RecipeEditor(
                recipeId = id,
                popBackStack = {navController.popBackStack()},
                navToProduct = {productId -> navController.navigate("${Screen.productEditor.name}?productId=${productId}&recipeId=${id}")}
            )
        }
        composable(
            route = "${Screen.productEditor.name}?productId={productId}&recipeId={recipeId}",
            arguments = listOf(
                navArgument("productId"){
                    defaultValue = -1
                    type = NavType.LongType
                },
                navArgument("recipeId"){
                    defaultValue = -1
                    type = NavType.LongType
                })){ backStackEntry ->
            ProductEditor(
                productId = backStackEntry.arguments!!.getLong("productId"),
                recipeId = backStackEntry.arguments!!.getLong("recipeId"),
                popBackStack = {navController.popBackStack()}
            )
        }
    }
}