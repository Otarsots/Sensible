package com.example.sensible.models


import com.google.gson.annotations.SerializedName

data class SourcesFields(
    @SerializedName("org-database-usda")
    val orgDatabaseUsda: OrgDatabaseUsda?
)