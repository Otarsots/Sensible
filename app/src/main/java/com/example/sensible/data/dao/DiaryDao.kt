package com.example.sensible.data.dao

import androidx.room.*
import com.example.sensible.models.DiaryEntry
import com.example.sensible.models.Goal
import com.example.sensible.models.ListItem
import com.example.sensible.models.Nutrients
import kotlinx.coroutines.flow.Flow

//TODO adapt from ProductDao
@Dao
interface DiaryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGoal(goal: Goal)

    @Update
    suspend fun updateGoal(goal: Goal)

    @Delete
    suspend fun deleteGoal(goal: Goal)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEntry(entry: DiaryEntry)

    @Query("DELETE FROM DiaryEntry WHERE date == :date AND recipeId == :recipeId")
    suspend fun deleteEntry(date: Long, recipeId: Long)

    @Query("SELECT calories, carbs,fat,protein FROM DateNutrients WHERE date == :date")
    fun getNutrients(date: Long): Flow<Nutrients?>

    @Query("SELECT * FROM Goal WHERE date == :date")
    fun getGoal(date: Long): Flow<Goal>

    @Query("SELECT * FROM Goal WHERE date = " +
            "(SELECT MAX(date) FROM Goal WHERE date <= :date)")
    fun getLastGoal(date: Long): Flow<Goal?>

    @Query("SELECT diary.recipeId as id, name, amount, (calories*amount/100) as calories FROM " +
            "(SELECT recipeId, amount FROM DiaryEntry WHERE date == :date) as diary " +
            "INNER JOIN RecipeNutrients " +
            "ON diary.recipeId = RecipeNutrients.recipeId " +
            "INNER JOIN Recipe " +
            "ON diary.recipeId = Recipe.recipeId ")
    fun getDiaryList(date: Long): Flow<List<ListItem>>
/*
    @Insert
    fun insert(day: Day)

    @Insert
    fun insertWithRecipes(diaryEntry: DiaryEntry, recipes: List<Recipe>)

    @Update
    fun update(diaryEntry: DiaryEntry)

    @Insert
    fun updateWithRecipes(diaryEntry: DiaryEntry, recipes: List<Recipe>)


 */
/*
    @Query("SELECT * FROM product_table WHERE productId = :id")
    fun findProduct(id: Long): List<Product>

    @Query("SELECT * FROM product_table WHERE productName = :name")
    fun findProduct(name: String): List<Product>

    @Query("DELETE FROM product_table WHERE productId = :id")
    fun deleteProduct(id: Long)

    @Query("DELETE FROM product_table WHERE productName = :name")
    fun deleteProduct(name: String)

    @Query("SELECT * FROM product_table")
    fun getAllProducts(): LiveData<List<Product>>

 */
}