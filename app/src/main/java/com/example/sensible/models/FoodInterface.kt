package com.example.sensible.models

import androidx.compose.ui.graphics.painter.Painter

interface FoodInterface {
    val name: String
    val imageURL: String
    val protein: Double
    val carbs: Double
    val fat: Double
    val calories: Double
    val amount: Long
}