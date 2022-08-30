package com.example.sensible.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.sensible.data.dao.ProductDao
import com.example.sensible.data.dao.RecipeDao
import com.example.sensible.models.Product
import com.example.sensible.models.Recipe
import com.example.sensible.models.RecipeProductCrossRef
import com.example.sensible.util.getProductData
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow

//TODO adapt from ProductRepository
class RecipeRepository(private val recipeDao: RecipeDao) {
    suspend fun insert(recipe: Recipe): Long {
        return recipeDao.insert(recipe)
    }

    suspend fun update(recipe: Recipe) {
        recipeDao.update(recipe)
    }

    fun getRecipe(recipeId: Long): Flow<Recipe?> {
        return recipeDao.getRecipe(recipeId)
    }

    suspend fun addProduct(recipeId: Long, productId: Long) {
        return recipeDao.addProduct(
            RecipeProductCrossRef(recipeId, productId)
        )
    }
}