package com.example.sensible.models


import com.google.gson.annotations.SerializedName

data class Missing(
    @SerializedName("agb_category")
    val agbCategory: Int?,
    @SerializedName("labels")
    val labels: Int?,
    @SerializedName("packagings")
    val packagings: Int?
)