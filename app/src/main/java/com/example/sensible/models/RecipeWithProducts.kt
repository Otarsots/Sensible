package com.example.sensible.models;

import androidx.room.*

@Entity(
    primaryKeys = ["recipeId", "productId"],
    foreignKeys = [
        ForeignKey(
            entity = Recipe::class,
            parentColumns = ["recipeId"],
            childColumns = ["recipeId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index(value = ["productId"])
    ])
data class RecipeProductCrossRef(
    val recipeId: Long,
    val productId: Long,
)

data class RecipeWithProducts (
    @Embedded
    val recipe: Recipe,
    @Relation(
        parentColumn = "recipeId",
        entityColumn = "productId",
        entity = Product::class,
        associateBy = Junction(RecipeProductCrossRef::class),
    )
    val productList: List<Product>
)