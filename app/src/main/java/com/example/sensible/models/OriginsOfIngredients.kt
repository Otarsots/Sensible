package com.example.sensible.models


import com.google.gson.annotations.SerializedName

data class OriginsOfIngredients(
    @SerializedName("aggregated_origins")
    val aggregatedOrigins: List<AggregatedOrigin>?,
    @SerializedName("epi_score")
    val epiScore: Int?,
    @SerializedName("epi_value")
    val epiValue: Int?,
    @SerializedName("origins_from_origins_field")
    val originsFromOriginsField: List<String>?,
    @SerializedName("transportation_scores")
    val transportationScores: TransportationScores?,
    @SerializedName("transportation_values")
    val transportationValues: TransportationValues?,
    @SerializedName("values")
    val values: Values?
)