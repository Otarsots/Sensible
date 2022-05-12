package com.example.sensible.models



class Food(val product: Product, override val amount: Long): FoodInterface {

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