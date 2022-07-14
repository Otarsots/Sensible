package com.example.sensible

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.sensible.ui.diary.list.DiaryEditor
import com.example.sensible.ui.diary.search.RecipeSearch
import com.example.sensible.ui.home.HomeBody
import com.example.sensible.ui.components.BarcodeScreen
import com.example.sensible.ui.recipe.editor.RecipeEditor
import com.example.sensible.ui.recipe.list.RecipeList

@Suppress("EnumEntryName")
enum class Screen {
    home,
    diaryEditor,
    diarySearch,
    recipeList,
    recipeEditor,
    recipeSearch,
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
            route = "${Screen.diaryEditor.name}?diaryId={diaryId}",
            arguments = listOf(
                navArgument("diaryId"){
                    defaultValue = -1
                    type = NavType.LongType
                })){ backStackEntry ->
            DiaryEditor(
                diaryId = backStackEntry.arguments!!.getLong("diaryId"),
                entries = emptyList(),
                navToRecipeEditor = {recipeId -> navController.navigate("${Screen.recipeEditor.name}?recipeId=$recipeId")},
                onAddClick = {navController.navigate(Screen.recipeSearch.name)},
                navToDate = {diaryId -> navController.navigate("${Screen.diaryEditor.name}?diaryId=$diaryId")}
            )
        }
        composable(Screen.recipeSearch.name){
            RecipeSearch(
                popBackStack = {navController.popBackStack()},
                onScanClick = {navController.navigate(Screen.barcodeScanner.name)}
            )
        }
        composable(Screen.recipeList.name){
            RecipeList()
        }
        composable(
            route = "${Screen.recipeEditor.name}?recipeId={recipeId}",
            arguments = listOf(
                navArgument("recipeId"){
                    defaultValue = -1
                    type = NavType.LongType
                })){ backStackEntry ->
            RecipeEditor(
                recipeId = backStackEntry.arguments!!.getLong("recipeId"),
                popBackStack = {navController.popBackStack()}
            )
        }
        composable(Screen.barcodeScanner.name){
            BarcodeScreen(
                onCodeScanned = {
                    navController.popBackStack()
                    navController.popBackStack()
                }
            )
        }
    }
}