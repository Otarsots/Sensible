package com.example.sensible.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity
data class Recipe(
    @PrimaryKey(autoGenerate = true)
    val recipeId: Long,
    val name: String,
    val isSingleIngredient: Boolean,
    val calories: Double,
    val protein: Double,
    val carbs: Double,
    val fat: Double,
)