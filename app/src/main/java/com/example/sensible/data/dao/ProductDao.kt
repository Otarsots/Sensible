package com.example.sensible.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.sensible.models.Product
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {

    @Insert
    fun insert(product: Product)

    @Update
    fun update(product: Product)

    @Delete
    fun delete(product: Product)

    @Query("SELECT * FROM Product ORDER BY productName COLLATE NOCASE ASC")
    fun getProducts(): Flow<List<Product>>

    @Query("SELECT * FROM Product WHERE productId == :id")
    fun getProduct(id: Int): Flow<Product?>
}