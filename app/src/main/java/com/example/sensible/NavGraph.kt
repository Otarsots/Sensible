package com.example.sensible

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.sensible.ui.diary.DiaryList
import com.example.sensible.ui.diary.editor.DiaryEditor
import com.example.sensible.ui.diary.search.FoodSearch
import com.example.sensible.ui.home.HomeBody
import com.example.sensible.ui.recipe.RecipeList
import com.example.sensible.ui.scanner.BarcodeScreen

@Suppress("EnumEntryName")
enum class Screen {
    home,
    diaryList,
    diaryCalendar,
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
            HomeBody(

            )
        }
        composable(Screen.diaryList.name) {
            DiaryList(
                entries = emptyList(),
                onEntryClick = {},
                onAddClick = {navController.navigate(Screen.diarySearch.name)},
            )
        }
        composable(Screen.diaryCalendar.name){

        }
        composable(Screen.diarySearch.name){
            FoodSearch(
                popBackStack = {navController.popBackStack()},
                onScanClick = {navController.navigate(Screen.barcodeScanner.name)}
            )
        }
        composable(
            /*
            route = "${Screen.diaryEditor.name}?diaryId={diaryId}",
            arguments = listOf(
                navArgument("diaryId"){
                    defaultValue = -1
                    type = NavType.IntType
                })){ backStackEntry ->
            DiaryEditor(
                diaryId = backStackEntry.arguments!!.getInt("diaryId"),
                popBackStack = { navController.popBackStack() }

             */
            Screen.diaryEditor.name
                ){
            DiaryEditor(
                diaryId = 1,
                popBackStack = {navController.popBackStack()})
        }
        composable(Screen.recipeList.name){
            RecipeList()
        }
        composable(Screen.recipeEditor.name){

        }
        composable(Screen.barcodeScanner.name){
            BarcodeScreen(
                onCodeScanned = {navController.popBackStack()}
            )
        }
    }
}