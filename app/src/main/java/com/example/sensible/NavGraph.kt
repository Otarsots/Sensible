package com.example.sensible

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.sensible.ui.diary.day.DiaryDay
import com.example.sensible.ui.diary.search.RecipeSearch
import com.example.sensible.ui.product.editor.ProductEditor
import com.example.sensible.ui.recipe.editor.RecipeEditor
import com.example.sensible.ui.recipe.list.RecipeList
import com.example.sensible.ui.settings.SettingsMenu
import com.example.sensible.ui.settings.goal.GoalSettings
import com.example.sensible.ui.statistics.StatisticsScreen
import java.time.LocalDate

@Suppress("EnumEntryName")
enum class Screen {
    statistics,
    diaryDay,
    diarySearch,
    recipeList,
    recipeEditor,
    recipeSearch,
    productEditor,
    barcodeScanner,
    settingsMenu,
    settingsGoals,
}

@Composable
fun SensibleNavHost(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(
        navController = navController,
        startDestination = Screen.recipeList.name,
        modifier = modifier
    ) {

        composable(Screen.statistics.name) {
            StatisticsScreen()
        }
        composable(
            route = "${Screen.diaryDay.name}?date={date}",
            arguments = listOf(
                navArgument("date"){
                    defaultValue = LocalDate.now().toEpochDay()
                    type = NavType.LongType
                })){ backStackEntry ->
            DiaryDay(
                date = backStackEntry.arguments!!.getLong("date"),
                //entries = emptyList(),
                navToRecipeEditor = {recipeId -> navController.navigate("${Screen.recipeEditor.name}?recipeId=$recipeId")},
                onAddClick = {date -> navController.navigate("${Screen.recipeSearch.name}?date=$date")},
                navToDate = {date -> navController.navigate("${Screen.diaryDay.name}?date=$date")},
                navToSettings = {navController.navigate(Screen.settingsMenu.name)}
            )
        }
        composable(
            route = "${Screen.recipeSearch.name}?date={date}",
            arguments = listOf(
                navArgument("date"){
                    defaultValue = LocalDate.now().toEpochDay()
                    type = NavType.LongType
                })){ backStackEntry ->
            RecipeSearch(
                date = backStackEntry.arguments!!.getLong("date"),
                popBackStack = {navController.popBackStack()},
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
        composable(Screen.settingsMenu.name){
            SettingsMenu(
                popBackStack = {navController.popBackStack()},
                navToGoalSettings = {navController.navigate(Screen.settingsGoals.name)}
            )
        }
        composable(Screen.settingsGoals.name){
            GoalSettings(
                popBackStack = {navController.popBackStack()}
            )
        }
    }
}