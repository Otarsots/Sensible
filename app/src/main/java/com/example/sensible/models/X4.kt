package com.example.sensible.models


import com.google.gson.annotations.SerializedName

data class X4(
    @SerializedName("sizes")
    val sizes: SizesXXXXXXXXX?,
    @SerializedName("uploaded_t")
    val uploadedT: Int?,
    @SerializedName("uploader")
    val uploader: String?
)