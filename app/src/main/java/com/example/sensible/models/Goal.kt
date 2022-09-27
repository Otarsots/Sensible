package com.example.sensible.models

import androidx.room.*

@Entity
data class Goal(
    @PrimaryKey
    val date: Long = 0,
    var caloriesGoal: Double = 0.0,
    var carbsGoal: Double = 0.0,
    var proteinGoal: Double = 0.0,
    var fatsGoal: Double = 0.0,
){

}