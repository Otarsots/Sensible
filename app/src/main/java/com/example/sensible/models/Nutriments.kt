package com.example.sensible.models


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Nutriments(
    @SerializedName("carbohydrates")
    val carbohydrates: Double,
    @Expose
    @SerializedName("carbohydrates_100g")
    val carbohydrates100g: Double,
    @Expose
    @SerializedName("carbohydrates_unit")
    val carbohydratesUnit: String,
    @SerializedName("carbohydrates_value")
    val carbohydratesValue: Double,
    @SerializedName("energy")
    val energy: Long,
    @Expose
    @SerializedName("energy_100g")
    val energy100g: Long,
    @SerializedName("energy-kcal")
    val energyKcal: Long,
    @SerializedName("energy-kcal_100g")
    val energyKcal100g: Long,
    @SerializedName("energy-kcal_unit")
    val energyKcalUnit: String,
    @SerializedName("energy-kcal_value")
    val energyKcalValue: Long,
    @Expose
    @SerializedName("energy_unit")
    val energyUnit: String,
    @SerializedName("energy_value")
    val energyValue: Long,
    @SerializedName("fat")
    val fat: Double,
    @Expose
    @SerializedName("fat_100g")
    val fat100g: Double,
    @Expose
    @SerializedName("fat_unit")
    val fatUnit: String,
    @SerializedName("fat_value")
    val fatValue: Double,
    @SerializedName("proteins")
    val proteins: Double,
    @Expose
    @SerializedName("proteins_100g")
    val proteins100g: Double,
    @Expose
    @SerializedName("proteins_unit")
    val proteinsUnit: String,
    @SerializedName("proteins_value")
    val proteinsValue: Double,
    @SerializedName("saturated-fat")
    val saturatedFat: Double,
    @SerializedName("saturated-fat_100g")
    val saturatedFat100g: Double,
    @SerializedName("saturated-fat_unit")
    val saturatedFatUnit: String,
    @SerializedName("saturated-fat_value")
    val saturatedFatValue: Double,
    @SerializedName("sugars")
    val sugars: Double,
    @SerializedName("sugars_100g")
    val sugars100g: Double,
    @SerializedName("sugars_unit")
    val sugarsUnit: String,
    @SerializedName("sugars_value")
    val sugarsValue: Double
)