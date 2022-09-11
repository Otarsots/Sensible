package com.example.sensible.data.dao

import androidx.room.*
import com.example.sensible.models.*
import kotlinx.coroutines.flow.Flow

//TODO adapt from ProductDao
@Dao
interface RecipeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(recipe: Recipe): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWithProducts(recipe: Recipe, products: List<Product>)

    @Update
    suspend fun update(product: Recipe)

    @Update
    suspend fun updateWithProducts(recipe: Recipe, products: List<Product>)

    @Delete
    suspend fun delete(recipe: Recipe)

    @Delete
    suspend fun delete(recipeProductCrossRef: RecipeProductCrossRef)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addProduct(recipeProductCrossRef: RecipeProductCrossRef)

    @Transaction
    @Query("SELECT * FROM Recipe WHERE recipeId == :recipeId")
    fun getRecipeWithProducts(recipeId: Long): Flow<RecipeWithIngredients>

    @Transaction
    @Query("SELECT * FROM Ingredient WHERE (productId == :productId AND recipeId == :recipeId)")
    fun getIngredient(recipeId: Long, productId: Long): Flow<Ingredient>


    @Query("SELECT * FROM Recipe")
    fun getRecipes(): Flow<List<Recipe>>

    @Query("SELECT * FROM Recipe WHERE recipeId == :recipeId")
    fun getRecipe(recipeId: Long): Flow<Recipe>
}