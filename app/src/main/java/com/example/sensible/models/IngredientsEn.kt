package com.example.sensible.models


import com.google.gson.annotations.SerializedName

data class IngredientsEn(
    @SerializedName("geometry")
    val geometry: String?,
    @SerializedName("imgid")
    val imgid: String?,
    @SerializedName("normalize")
    val normalize: String?,
    @SerializedName("rev")
    val rev: String?,
    @SerializedName("sizes")
    val sizes: SizesXXX?,
    @SerializedName("white_magic")
    val whiteMagic: Any?
)