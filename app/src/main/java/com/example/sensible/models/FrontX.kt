package com.example.sensible.models


import com.google.gson.annotations.SerializedName

data class FrontX(
    @SerializedName("display")
    val display: Display?,
    @SerializedName("small")
    val small: Small?,
    @SerializedName("thumb")
    val thumb: Thumb?
)