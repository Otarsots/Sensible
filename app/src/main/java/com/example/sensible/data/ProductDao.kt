package com.example.sensible.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.sensible.models.Product

@Dao
interface ProductDao {

    @Insert
    fun insertProduct(product: Product)

    @Query("SELECT * FROM Product WHERE id = :id")
    fun findProduct(id: Long): List<Product>

    @Query("SELECT * FROM Product WHERE productName = :name")
    fun findProduct(name: String): List<Product>

    @Query("DELETE FROM Product WHERE id = :id")
    fun deleteProduct(id: Long)

    @Query("DELETE FROM Product WHERE productName = :name")
    fun deleteProduct(name: String)

    @Query("SELECT * FROM Product")
    fun getAllProducts(): LiveData<List<Product>>
}