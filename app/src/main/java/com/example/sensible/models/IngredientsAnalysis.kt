package com.example.sensible.models


import com.google.gson.annotations.SerializedName

data class IngredientsAnalysis(
    @SerializedName("en:palm-oil-content-unknown")
    val enPalmOilContentUnknown: List<String>?,
    @SerializedName("en:vegan-status-unknown")
    val enVeganStatusUnknown: List<String>?,
    @SerializedName("en:vegetarian-status-unknown")
    val enVegetarianStatusUnknown: List<String>?
)