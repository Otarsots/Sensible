package com.example.sensible.models


import com.google.gson.annotations.SerializedName

data class X100XXX(
    @SerializedName("h")
    val h: Int?,
    @SerializedName("w")
    val w: Int?
)