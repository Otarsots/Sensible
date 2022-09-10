package com.example.sensible.data.dao

import androidx.room.*
import com.example.sensible.models.Product
import com.example.sensible.models.Recipe
import com.example.sensible.models.RecipeProductCrossRef
import com.example.sensible.models.RecipeWithIngredients
import kotlinx.coroutines.flow.Flow

//TODO adapt from ProductDao
@Dao
interface RecipeDao {

    @Insert
    suspend fun insert(recipe: Recipe): Long

    @Insert
    suspend fun insertWithProducts(recipe: Recipe, products: List<Product>)

    @Update
    suspend fun update(product: Recipe)

    @Update
    suspend fun updateWithProducts(recipe: Recipe, products: List<Product>)

    @Delete
    suspend fun delete(recipe: Recipe)

    @Delete
    suspend fun delete(recipeProductCrossRef: RecipeProductCrossRef)

    @Insert
    suspend fun addProduct(recipeProductCrossRef: RecipeProductCrossRef)

    @Transaction
    @Query("SELECT * FROM Recipe WHERE recipeId == :recipeId")
    fun getRecipeWithProducts(recipeId: Long): Flow<RecipeWithIngredients>

    @Query("SELECT * FROM Recipe")
    fun getRecipes(): Flow<List<Recipe>>

    @Query("SELECT * FROM Recipe WHERE recipeId == :recipeId")
    fun getRecipe(recipeId: Long): Flow<Recipe?>
}