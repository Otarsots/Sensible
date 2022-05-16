package com.example.sensible

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.ui.graphics.vector.ImageVector

sealed class SensibleScreen(
    val route: String,
    @StringRes val name: Int,
    @DrawableRes val icon: Int
) {
    object Home: SensibleScreen("home", R.string.home_name, R.drawable.ic_launcher_foreground)
    object Diary: SensibleScreen("diary", R.string.diary_name, R.drawable.ic_launcher_foreground)
    object Recipes: SensibleScreen("recipes", R.string.recipes_name, R.drawable.ic_launcher_foreground)

    companion object {
        fun fromRoute(route: String?): SensibleScreen =
            when (route?.substringBefore("/")) {
                Home.route -> Home
                Diary.route -> Diary
                Recipes.route -> Recipes
                null -> Home
                else -> throw IllegalArgumentException("Route $route is not recognized.")
            }
    }
}