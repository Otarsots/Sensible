package com.example.sensible.models;

import androidx.room.*

@Entity(
    primaryKeys = ["recipeId", "productId"],
    /*
    foreignKeys = [
        ForeignKey(
            entity = Recipe::class,
            parentColumns = ["recipeId"],
            childColumns = ["recipeId"],
            onDelete = ForeignKey.CASCADE
        )
    ],

     */
    indices = [
        Index(value = ["productId"])
    ])
data class RecipeProductCrossRef(
    val recipeId: Long,
    val productId: Long,
    val amount: Long,
)

@DatabaseView("SELECT * FROM RecipeProductCrossRef INNER JOIN Product ON RecipeProductCrossRef.productId = Product.productId")
data class Ingredient(
    @Embedded
    val product: Product,
    val amount: Long
)

data class RecipeWithIngredients (
    @Embedded
    val recipe: Recipe,
    @Relation(
        parentColumn = "recipeId",
        entityColumn = "productId",
        associateBy = Junction(RecipeProductCrossRef::class),
    )
    val productList: List<Ingredient>
)