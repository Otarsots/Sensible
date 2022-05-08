package com.example.sensible.models


import com.google.gson.annotations.SerializedName

data class AggregatedOrigin(
    @SerializedName("origin")
    val origin: String?,
    @SerializedName("percent")
    val percent: Int?
)