package com.example.sensible.models


class Recipe(private val ingredients: List<Food>, override val amount: Long, val Portions: Long): FoodInterface {


    override val protein: Double
        get() {
            var count = 0.0
             for(ingredient:Food in this.ingredients){
                count += ingredient.protein
            }
            return count*amount/(100*Portions)
        }

    override val carbs: Double
        get() {
            var count = 0.0
            for(ingredient:Food in this.ingredients){
                count += ingredient.carbs
            }
            return count*amount/(100*Portions)
        }

    override val fat: Double
        get() {
            var count = 0.0
            for(ingredient:Food in this.ingredients){
                count += ingredient.fat
            }
            return count*amount/(100*Portions)
        }

    override val calories: Double
        get() {
            var count = 0.0
            for(ingredient:Food in this.ingredients){
                count += ingredient.calories
            }
            return count*amount/(100*Portions)
        }
}