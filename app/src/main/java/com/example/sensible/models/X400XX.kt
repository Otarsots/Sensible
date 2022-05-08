package com.example.sensible.models


import com.google.gson.annotations.SerializedName

data class X400XX(
    @SerializedName("h")
    val h: Int?,
    @SerializedName("w")
    val w: Int?
)