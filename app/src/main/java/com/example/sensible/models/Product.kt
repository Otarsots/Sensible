package com.example.sensible.models


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.time.temporal.TemporalAmount

@Entity
data class Product(
    val amount: Long = 100,
    @SerializedName("carbohydrates_100g")
    val carbohydrates100g: Double,
    @SerializedName("id")
    @PrimaryKey
    val productId: Long,
    @SerializedName("energy-kcal_100g")
    val energyKcal100g: Double,
    @SerializedName("fat_100g")
    val fat100g: Double,
    @SerializedName("product_name")
    val productName: String,
    @SerializedName("proteins_100g")
    val proteins100g: Double
)