package com.example.sensible.data.repository

import com.example.sensible.data.dao.RecipeDao
import com.example.sensible.models.Ingredient
import com.example.sensible.models.Nutrients
import com.example.sensible.models.Recipe
import com.example.sensible.models.RecipeProductCrossRef
import kotlinx.coroutines.flow.Flow

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

    suspend fun deleteRecipe(recipeId: Long){
        //recipeDao.deleteIngredients(recipeId)
        recipeDao.deleteRecipe(recipeId)

    }

    suspend fun updateIngredient(recipeId: Long, productId: Long, amount: Long) {
        return recipeDao.updateIngredient(RecipeProductCrossRef(recipeId,productId,amount))
    }

    fun getNutrients(recipeId: Long): Flow<Nutrients> {
        return recipeDao.getNutrients(recipeId)
    }

    fun getIngredients(recipeId: Long): Flow<List<Ingredient>>{
        return recipeDao.getIngredients(recipeId)
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