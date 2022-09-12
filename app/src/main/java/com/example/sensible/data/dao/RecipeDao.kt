package com.example.sensible.data.dao

import androidx.room.*
import com.example.sensible.models.*
import kotlinx.coroutines.flow.Flow

//TODO adapt from ProductDao
@Dao
interface RecipeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(recipe: Recipe): Long

    @Update
    suspend fun update(product: Recipe)

    @Update
    suspend fun updateIngredient(recipeProductCrossRef: RecipeProductCrossRef)

    @Delete
    suspend fun delete(recipeProductCrossRef: RecipeProductCrossRef)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addProduct(recipeProductCrossRef: RecipeProductCrossRef)

    @Query("SELECT SUM(energyKcal100g/100*amount) as calories, " +
            "SUM(carbohydrates100g/100*amount) as carbs," +
            "SUM(fat100g/100*amount) as fat," +
            "SUM(proteins100g/100*amount) as protein FROM Ingredient WHERE recipeId == :recipeId")
    fun getNutrients(recipeId: Long): Flow<Nutrients>

    /*
    @Query("DELETE FROM RecipeProductCrossRef WHERE recipeId == :recipeId")
    suspend fun deleteIngredients(recipeId: Long)
     */

    @Query("DELETE FROM Recipe WHERE recipeId == :recipeId")
    suspend fun deleteRecipe(recipeId: Long)

    @Transaction
    @Query("SELECT * FROM Ingredient WHERE (productId == :productId AND recipeId == :recipeId)")
    fun getIngredient(recipeId: Long, productId: Long): Flow<Ingredient>

    @Transaction
    @Query("SELECT * FROM Ingredient WHERE recipeId == :recipeId")
    fun getIngredients(recipeId: Long): Flow<List<Ingredient>>

    @Query("SELECT * FROM Recipe")
    fun getRecipes(): Flow<List<Recipe>>

    @Query("SELECT * FROM Recipe WHERE recipeId == :recipeId")
    fun getRecipe(recipeId: Long): Flow<Recipe>
}