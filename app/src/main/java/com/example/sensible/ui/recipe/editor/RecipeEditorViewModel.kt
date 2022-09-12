package com.example.sensible.ui.recipe.editor

import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sensible.data.repository.ProductRepository
import com.example.sensible.data.repository.RecipeRepository
import com.example.sensible.models.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.time.temporal.TemporalAmount


class RecipeEditorViewModel(
    private val recipeRepository: RecipeRepository,
    private val productRepository: ProductRepository,
    private val recipeId: Long,
): ViewModel() {
    private var _recipe = MutableStateFlow<Recipe>((Recipe()))
    val recipe = _recipe.asStateFlow()

    private var _name = MutableStateFlow("")
    val name = _name.asStateFlow()

    private var _ingredients = MutableStateFlow<List<Ingredient>>(emptyList())
    val ingredients = _ingredients.asStateFlow()

    private var _nutrients = MutableStateFlow<Nutrients>(Nutrients())
    val nutrients = _nutrients.asStateFlow()

    init {
        viewModelScope.launch {
            launch {
                recipeRepository.getRecipe(recipeId).collect {
                    _recipe.value = it
                    _name.value = it.name
                }
            }
            launch {
                recipeRepository.getIngredients(recipeId).collect {
                    _ingredients.value = it
                }
            }
            launch {
                recipeRepository.getNutrients(recipeId).collect{
                    _nutrients.value = it
                }
            }
        }
    }

    fun getProduct(productId: Long) = runBlocking { productRepository.fetchProduct(productId) }

    fun setName(name: String) {
        _name.value = name
    }

    fun updateName(name: String) {
        _recipe.let {
            viewModelScope.launch {
                recipeRepository.update(it.value.copy(name = name))
            }
        }
    }

    fun addProduct(productId: Long, amount: Long, onComplete: () -> Unit) {
            viewModelScope.launch {
                recipeRepository.addProduct(recipeId,productId, amount)
            }.invokeOnCompletion {
                onComplete()
            }
    }
    fun getProportions(): List<Float>{
        nutrients.value.let {
            val carbCals = it.carbs.toFloat()*4
            val fatCals = it.fat.toFloat()*9
            val proteinCals = it.protein.toFloat()*4
            val cals = carbCals + fatCals + proteinCals
            return listOf(carbCals/cals, fatCals/cals, proteinCals/cals)
        }

    }

    fun removeIngredient(productId: Long) {
        viewModelScope.launch {
            recipeRepository.removeIngredient(recipeId, productId)
        }
    }

}