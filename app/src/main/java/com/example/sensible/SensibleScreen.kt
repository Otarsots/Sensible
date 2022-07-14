package com.example.sensible

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

sealed class SensibleScreen(
    val route: String,
    @StringRes val name: Int,
    @DrawableRes val icon: Int
) {
    object Home: SensibleScreen(Screen.home.name, R.string.home_name, R.drawable.ic_launcher_home)
    object DiaryEditor: SensibleScreen(Screen.diaryEditor.name, R.string.diary_name, R.drawable.ic_launcher_diary)
    object RecipeList: SensibleScreen(Screen.recipeList.name, R.string.recipes_name, R.drawable.ic_launcher_recipes)

    /*
    companion object {
        fun fromRoute(route: String?): SensibleScreen =
            when (route?.substringBefore("/")) {
                Home.route -> Home
                DiaryEditor.route -> DiaryEditor
                RecipeList.route -> RecipeList
                null -> Home
                else -> throw IllegalArgumentException("Route $route is not recognized.")
            }
    }

     */
}