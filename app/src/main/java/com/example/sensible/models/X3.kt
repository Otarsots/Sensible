package com.example.sensible.models


import com.google.gson.annotations.SerializedName

data class X3(
    @SerializedName("sizes")
    val sizes: SizesXXXXXXXX?,
    @SerializedName("uploaded_t")
    val uploadedT: Int?,
    @SerializedName("uploader")
    val uploader: String?
)