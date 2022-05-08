package com.example.sensible.models


import com.google.gson.annotations.SerializedName

data class ProductionSystem(
    @SerializedName("labels")
    val labels: List<Any>?,
    @SerializedName("value")
    val value: Int?,
    @SerializedName("warning")
    val warning: String?
)