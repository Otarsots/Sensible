package com.example.sensible.models


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Product(
    //val amount: Long = 100,
    @SerializedName("carbohydrates_100g")
    val carbohydrates100g: Double = 0.0,
    @SerializedName("code")
    @PrimaryKey
    var productId: Long = 0,
    @SerializedName("energy-kcal_100g")
    val energyKcal100g: Double = 0.0,
    @SerializedName("fat_100g")
    val fat100g: Double = 0.0,
    @SerializedName("product_name")
    val productName: String = "",
    @SerializedName("proteins_100g")
    val proteins100g: Double = 0.0
)