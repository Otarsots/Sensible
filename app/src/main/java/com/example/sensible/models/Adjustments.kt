package com.example.sensible.models


import com.google.gson.annotations.SerializedName

data class Adjustments(
    @SerializedName("origins_of_ingredients")
    val originsOfIngredients: OriginsOfIngredients?,
    @SerializedName("packaging")
    val packaging: Packaging?,
    @SerializedName("production_system")
    val productionSystem: ProductionSystem?,
    @SerializedName("threatened_species")
    val threatenedSpecies: ThreatenedSpecies?
)