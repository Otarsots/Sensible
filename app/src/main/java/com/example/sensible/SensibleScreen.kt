package com.example.sensible

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

sealed class SensibleScreen(
    val route: String,
    @StringRes val name: Int,
    @DrawableRes val icon: Int
) {
    object DiaryDay: SensibleScreen(Screen.diaryDay.name, R.string.diary_name, R.drawable.ic_action_diary)
    object RecipeList: SensibleScreen(Screen.recipeList.name, R.string.recipes_name, R.drawable.ic_action_recipes)
    object Statistics: SensibleScreen(Screen.statistics.name, R.string.statistics_name, R.drawable.ic_action_statistics)

}