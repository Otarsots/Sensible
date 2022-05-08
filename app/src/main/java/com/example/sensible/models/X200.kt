package com.example.sensible.models


import com.google.gson.annotations.SerializedName

data class X200(
    @SerializedName("h")
    val h: Int?,
    @SerializedName("w")
    val w: Int?
)