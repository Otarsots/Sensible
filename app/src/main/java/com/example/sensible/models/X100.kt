package com.example.sensible.models


import com.google.gson.annotations.SerializedName

data class X100(
    @SerializedName("h")
    val h: Int?,
    @SerializedName("w")
    val w: Int?
)