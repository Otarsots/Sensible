package com.example.sensible.models

import androidx.room.DatabaseView
import androidx.room.Embedded

data class Nutrients (
    val calories: Double = 0.0,
    val carbs: Double = 0.0,
    val fat: Double = 0.0,
    val protein: Double = 0.0,
        )

@DatabaseView("SELECT recipeId," +
        "SUM(energyKcal100g/100*amount) as calories, " +
        "SUM(carbohydrates100g/100*amount) as carbs," +
        "SUM(fat100g/100*amount) as fat," +
        "SUM(proteins100g/100*amount) as protein FROM Ingredient")
data class RecipeNutrients(
    val recipeId: Long,
    @Embedded
    val nutrients: Nutrients
)

@DatabaseView("SELECT date," +
        "SUM(calories/100*amount) as calories, " +
        "SUM(carbs/100*amount) as carbs," +
        "SUM(fat/100*amount) as fat," +
        "SUM(protein/100*amount) as protein FROM RecipeNutrients INNER JOIN DiaryEntry " +
        "ON RecipeNutrients.recipeId = DiaryEntry.recipeId")
data class DateNutrients(
    val date: Long,
    @Embedded
    val nutrients: Nutrients
)