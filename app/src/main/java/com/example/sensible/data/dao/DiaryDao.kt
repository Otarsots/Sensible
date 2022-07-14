package com.example.sensible.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.sensible.models.DiaryEntry
import com.example.sensible.models.Product
import com.example.sensible.models.Recipe

//TODO adapt from ProductDao
@Dao
interface DiaryDao {

    @Insert
    fun insert(diaryEntry: DiaryEntry)

    @Insert
    fun insertWithRecipes(diaryEntry: DiaryEntry, recipes: List<Recipe>)

    @Update
    fun update(diaryEntry: DiaryEntry)

    @Insert
    fun updateWithRecipes(diaryEntry: DiaryEntry, recipes: List<Recipe>)

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