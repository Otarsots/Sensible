package com.example.sensible.models


import com.google.gson.annotations.SerializedName

data class EcoscoreData(
    @SerializedName("adjustments")
    val adjustments: Adjustments?,
    @SerializedName("agribalyse")
    val agribalyse: Agribalyse?,
    @SerializedName("missing")
    val missing: Missing?,
    @SerializedName("missing_agribalyse_match_warning")
    val missingAgribalyseMatchWarning: Int?,
    @SerializedName("status")
    val status: String?
)