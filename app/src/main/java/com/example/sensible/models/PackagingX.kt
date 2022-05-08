package com.example.sensible.models


import com.google.gson.annotations.SerializedName

data class PackagingX(
    @SerializedName("ecoscore_material_score")
    val ecoscoreMaterialScore: Int?,
    @SerializedName("ecoscore_shape_ratio")
    val ecoscoreShapeRatio: Int?,
    @SerializedName("material")
    val material: String?,
    @SerializedName("shape")
    val shape: String?
)