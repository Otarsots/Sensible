package com.example.sensible.models


import com.google.gson.annotations.SerializedName

data class X1(
    @SerializedName("sizes")
    val sizes: SizesXXXXXX?,
    @SerializedName("uploaded_t")
    val uploadedT: Int?,
    @SerializedName("uploader")
    val uploader: String?
)