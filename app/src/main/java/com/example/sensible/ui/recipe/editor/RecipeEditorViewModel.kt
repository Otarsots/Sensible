package com.example.sensible.ui.recipe.editor

import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sensible.data.repository.ProductRepository
import com.example.sensible.data.repository.RecipeRepository
import com.example.sensible.models.Ingredient
import com.example.sensible.models.Product
import com.example.sensible.models.Recipe
import com.example.sensible.models.RecipeWithIngredients
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
    private var _recipe = MutableStateFlow<RecipeWithIngredients>(RecipeWithIngredients(Recipe(),emptyList()))
    val recipe = _recipe.asStateFlow()

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
                recipeRepository.getRecipeWithProducts(recipeId).collect {
                    _recipe.value = it
                    /*
                    _calories.value = it.calories
                    _carbs.value = it.carbs
                    _fat.value = it.fat
                    _protein.value = it.protein

                     */
                }
                updateNutriments()
            }
        }
    }

    fun getProduct(productId: Long) = runBlocking { productRepository.fetchProduct(productId) }


    fun updateName(name: String) {
        _recipe.let {
            viewModelScope.launch {
                recipeRepository.update(it.value.recipe.copy(name = name))
            }
        }
    }

    fun addProduct(productId: Long, amount: Long) {
            viewModelScope.launch {
                recipeRepository.addProduct(recipeId,productId, amount)
                updateNutriments()
            }
    }
    fun getProportions(): List<Float>{
        recipe.let {
            val carbCals = it.value.recipe.carbs.toFloat()*4
            val fatCals = it.value.recipe.fat.toFloat()*9
            val proteinCals = it.value.recipe.protein.toFloat()*4
            val cals = carbCals + fatCals + proteinCals
            return listOf(carbCals/cals, fatCals/cals, proteinCals/cals)
        }?: return emptyList()

    }

    fun removeIngredient(productId: Long) {
        viewModelScope.launch {
            recipeRepository.removeIngredient(recipeId, productId)
            updateNutriments()
        }
    }

    private fun updateNutriments(){
        viewModelScope.launch {
            var calories = 0.0
            var carbs = 0.0
            var fat = 0.0
            var protein = 0.0
            recipeRepository.getRecipeWithProducts(recipeId).collect{res ->
                val ingredients = res.productList
                for(ing in ingredients) {
                    calories += ing.product.energyKcal100g*ing.amount/100
                    carbs += ing.product.carbohydrates100g*ing.amount/100
                    fat += ing.product.fat100g*ing.amount/100
                    protein += ing.product.proteins100g*ing.amount/100
                }
                /*
                rec = res.recipe.copy(
                    calories = calories,
                    carbs = carbs,
                    fat = fat,
                    protein = protein)

                 */
                }
            //recipeRepository.update(rec)
            }
        }

}