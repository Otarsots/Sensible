package com.example.sensible.models


import com.google.gson.annotations.SerializedName

data class Agribalyse(
    @SerializedName("warning")
    val warning: String?
)