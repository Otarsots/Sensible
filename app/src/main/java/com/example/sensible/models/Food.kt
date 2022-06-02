package com.example.sensible.models

import androidx.compose.ui.graphics.painter.Painter
import coil.compose.rememberAsyncImagePainter


class Food(val product: Product, override val amount: Long): FoodInterface {
    override val name: String
        get() = product.productName

    override val imageURL: String
        get() = product.imageUrl.orEmpty()

    override val protein: Double
        get() {
            return product.nutriments.proteins100g * amount / 100
        }

    override val carbs: Double
        get() {
            return product.nutriments.carbohydrates100g * amount / 100
        }

    override val fat: Double
        get() {
            return product.nutriments.fat100g * amount / 100
        }

    override val calories: Double
        get() {
            return (product.nutriments.energy100g * amount / 100).toDouble()
        }
}