package com.example.sensible.ui.recipe.editor

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sensible.data.repository.ProductRepository
import com.example.sensible.data.repository.RecipeRepository
import com.example.sensible.models.Recipe
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


class RecipeEditorViewModel(
    private val recipeRepository: RecipeRepository,
    private val productRepository: ProductRepository,
    private val recipeId: Long,
): ViewModel() {
    private val _recipeFlow = recipeRepository.getRecipe(recipeId)
    private var _recipe: Recipe? = null

    init {
        viewModelScope.launch {
            launch {
                _recipeFlow.collect {_recipe = it}
            }
        }
    }

    fun getProduct(productId: Long) = runBlocking { productRepository.getProduct(productId) }

    fun updateName(name: String) {
        _recipe?.let {
            viewModelScope.launch {
                recipeRepository.update(it.copy(name = name))
            }
        }
    }

    fun addProduct(productId: Long) {
        _recipe?.let { recipe ->
            viewModelScope.launch {
                recipeRepository
            }
        }
    }
}