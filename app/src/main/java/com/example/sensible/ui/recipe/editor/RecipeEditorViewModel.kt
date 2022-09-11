package com.example.sensible.ui.recipe.editor

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sensible.data.repository.ProductRepository
import com.example.sensible.data.repository.RecipeRepository
import com.example.sensible.models.Recipe
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.time.temporal.TemporalAmount


class RecipeEditorViewModel(
    private val recipeRepository: RecipeRepository,
    private val productRepository: ProductRepository,
    recipeId: Long,
): ViewModel() {
    private val _recipeFlow = recipeRepository.getRecipe(recipeId)
    private var _recipe: Recipe? = null
    val recipe = recipeRepository.getRecipeWithProducts(recipeId)

    private val _calories = MutableStateFlow<Double>(0.0)
    val calories = _calories.asStateFlow()

    private val _carbs = MutableStateFlow<Double>(0.0)
    val carbs = _carbs.asStateFlow()

    private val _fat = MutableStateFlow<Double>(0.0)
    val fat = _fat.asStateFlow()

    private val _protein = MutableStateFlow<Double>(0.0)
    val protein = _protein.asStateFlow()

    init {
        viewModelScope.launch {
            launch {
                _recipeFlow.collect {
                    _recipe = it
                    _calories.value = it.calories
                    _carbs.value = it.carbs
                    _fat.value = it.fat
                    _protein.value = it.protein
                }
            }
        }
    }

    fun getProduct(productId: Long) = runBlocking { productRepository.fetchProduct(productId) }


    fun updateName(name: String) {
        _recipe?.let {
            viewModelScope.launch {
                recipeRepository.update(it.copy(name = name))
            }
        }
    }

    fun addProduct(productId: Long, amount: Long) {
        _recipe?.let { recipe ->
            viewModelScope.launch {
                recipeRepository.addProduct(recipe.recipeId,productId, amount)
            }
        }
    }
    fun getProportions(): List<Float>{
        val carbCals = carbs.value.toFloat()*4
        val fatCals = fat.value.toFloat()*9
        val proteinCals = protein.value.toFloat()*4
        val cals = carbCals + fatCals + proteinCals
        return listOf(carbCals/cals, fatCals/cals, proteinCals/cals)
    }
}