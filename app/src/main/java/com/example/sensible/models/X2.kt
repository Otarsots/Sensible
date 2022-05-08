package com.example.sensible.models


import com.google.gson.annotations.SerializedName

data class X2(
    @SerializedName("sizes")
    val sizes: SizesXXXXXXX?,
    @SerializedName("uploaded_t")
    val uploadedT: Int?,
    @SerializedName("uploader")
    val uploader: String?
)