package com.example.sensible.data.dao

import androidx.room.*
import com.example.sensible.models.Product
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(product: Product)

    @Update
    suspend fun update(product: Product)

    @Delete
    suspend fun delete(product: Product)

    @Query("SELECT * FROM Product ORDER BY productName COLLATE NOCASE ASC")
    fun getProducts(): Flow<List<Product>>

    @Query("SELECT * FROM Product WHERE productId == :id")
    fun getProduct(id: Long): Flow<Product?>
}