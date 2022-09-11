package com.example.sensible.data.repository

import com.example.sensible.data.dao.RecipeDao
import com.example.sensible.models.Ingredient
import com.example.sensible.models.Recipe
import com.example.sensible.models.RecipeProductCrossRef
import com.example.sensible.models.RecipeWithIngredients
import kotlinx.coroutines.flow.Flow

//TODO adapt from ProductRepository
class RecipeRepository(private val recipeDao: RecipeDao) {

    val recipes = recipeDao.getRecipes()

    suspend fun insert(recipe: Recipe): Long {
        return recipeDao.insert(recipe)
    }

    suspend fun update(recipe: Recipe) {
        recipeDao.update(recipe)
    }

    fun getRecipe(recipeId: Long): Flow<Recipe> {
        return recipeDao.getRecipe(recipeId)
    }

    fun getRecipeWithProducts(recipeId: Long): Flow<RecipeWithIngredients> {
        return recipeDao.getRecipeWithProducts(recipeId)
    }

    fun getIngredient(recipeId: Long, productId: Long): Flow<Ingredient>{
        return recipeDao.getIngredient(recipeId, productId)
    }

    suspend fun removeIngredient(recipeId: Long, productId: Long){
        return recipeDao.delete(RecipeProductCrossRef(recipeId,productId))
    }

    suspend fun addProduct(recipeId: Long, productId: Long, amount: Long) {
        return recipeDao.addProduct(
            RecipeProductCrossRef(recipeId, productId, amount)
        )
    }

}