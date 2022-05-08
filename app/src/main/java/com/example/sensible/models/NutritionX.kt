package com.example.sensible.models


import com.google.gson.annotations.SerializedName

data class NutritionX(
    @SerializedName("display")
    val display: DisplayXX?,
    @SerializedName("small")
    val small: SmallXX?,
    @SerializedName("thumb")
    val thumb: ThumbXX?
)