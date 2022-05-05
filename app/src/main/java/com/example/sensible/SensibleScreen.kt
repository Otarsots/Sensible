package com.example.sensible

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.ui.graphics.vector.ImageVector

enum class SensibleScreen(
    val icon: ImageVector,
) {
    Home(
        icon = Icons.Filled.Favorite,
    ),
    Diary(
        icon = Icons.Filled.Favorite,
    ),
    Recipes(
        icon = Icons.Filled.Favorite,
    );

    companion object {
        fun fromRoute(route: String?): SensibleScreen =
            when (route?.substringBefore("/")) {
                Home.name -> Home
                Diary.name -> Diary
                Recipes.name -> Recipes
                null -> Home
                else -> throw IllegalArgumentException("Route $route is not recognized.")
            }
    }
}