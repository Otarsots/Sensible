package com.example.sensible.models


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ProductResult(
    @SerializedName("code")
    val code: String?,
    @Expose
    @SerializedName("product")
    val product: Product?,
    @SerializedName("status")
    val status: Int?,
    @SerializedName("status_verbose")
    val statusVerbose: String?
)