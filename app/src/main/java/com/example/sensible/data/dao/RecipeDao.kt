package com.example.sensible.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.sensible.models.Product
import com.example.sensible.models.Recipe
import com.example.sensible.models.RecipeProductCrossRef
import com.example.sensible.models.RecipeWithProducts
import kotlinx.coroutines.flow.Flow

//TODO adapt from ProductDao
@Dao
interface RecipeDao {

    @Insert
    suspend fun insert(recipe: Recipe)

    @Insert
    suspend fun insertWithProducts(recipe: Recipe, products: List<Product>)

    @Update
    suspend fun update(product: Product)

    @Update
    suspend fun updateWithProducts(recipe: Recipe, products: List<Product>)

    @Delete
    suspend fun delete(recipe: Recipe)

    @Delete
    suspend fun delete(recipeProductCrossRef: RecipeProductCrossRef)

    @Transaction
    @Query("SELECT * FROM Recipe")
    fun getRecipesWithProducts(): Flow<List<RecipeWithProducts>>

    @Query("SELECT * FROM Recipe WHERE recipeId == :recipeId")
    fun getRecipe(recipeId: Int): Flow<Recipe?>
}