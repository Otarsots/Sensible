package com.example.sensible.models

import androidx.room.*

@Entity
data class Day(
    @PrimaryKey
    val date: Long,
    val calories: Double,
    val carbs: Double,
    val protein: Double,
    val fats: Double,
    val caloriesGoal: Double,
    val carbsGoal: Double,
    val proteinGoal: Double,
    val fatsGoal: Double,
){

}