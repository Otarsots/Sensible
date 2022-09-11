package com.example.sensible.ui.product.editor

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sensible.data.repository.ProductRepository
import com.example.sensible.data.repository.RecipeRepository
import com.example.sensible.models.Ingredient
import com.example.sensible.models.Product
import com.example.sensible.models.Recipe
import com.example.sensible.models.RecipeProductCrossRef
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class ProductEditorViewModel(
    private val productRepository: ProductRepository,
    private val recipeRepository: RecipeRepository,
    private val ids: Pair<Long, Long>
): ViewModel() {
    private val recipeId = ids.first
    private val productId = ids.second
    private var _ingredient = MutableStateFlow<Ingredient>(Ingredient(recipeId = recipeId,
        product = Product(productId = productId),amount = 100))
    val ingredient = _ingredient.asStateFlow()

    private val _name = MutableStateFlow("")
    val name = _name.asStateFlow()

    private val _image = MutableStateFlow("")
    val image = _image.asStateFlow()

    private val _calories = MutableStateFlow<Double>(0.0)
    val calories = _calories.asStateFlow()

    private val _carbs = MutableStateFlow<Double>(0.0)
    val carbs = _carbs.asStateFlow()

    private val _fat = MutableStateFlow<Double>(0.0)
    val fat = _fat.asStateFlow()

    private val _protein = MutableStateFlow<Double>(0.0)
    val protein = _protein.asStateFlow()

    private val _amount = MutableStateFlow<Long>(0)
    val amount = _amount.asStateFlow()


    init {
        viewModelScope.launch {
             recipeRepository.getIngredient(recipeId,productId).collect{
                 _ingredient.value = it
                 _name.value = it.product.productName
                 _image.value = it.product.imageUrl
                 _amount.value = it.amount
                 setAmount(it.amount)
             }
            launch {
                name.collect()
            }
            launch {
                calories.collect()
            }
            launch {
                carbs.collect()
            }
            launch {
                fat.collect()
            }
            launch {
                protein.collect()
            }
            launch {
                amount.collect()
            }
            launch {
                image.collect()
            }

        }
    }

    fun setName(name: String) {
        _name.value = name
    }

    fun setCalories(calories: Double) {
        _calories.value = calories
    }
    fun setCarbs(carbs: Double) {
        _carbs.value = carbs
    }

    fun setFat(fat: Double) {
        _fat.value = fat
    }

    fun setProtein(protein: Double) {
        _protein.value = protein
    }

    fun save(onComplete: () -> Unit){
        viewModelScope.launch {
            recipeRepository.updateIngredient(recipeId, productId, amount.value)
        }.invokeOnCompletion {
            onComplete()
        }
    }

    fun getProportions(): List<Float>{
        val carbCals = _ingredient.value.product.carbohydrates100g.toFloat()*4
        val fatCals = _ingredient.value.product.fat100g.toFloat()*9
        val proteinCals = _ingredient.value.product.proteins100g.toFloat()*4
        val cals = carbCals + fatCals + proteinCals
        return listOf(carbCals/cals, fatCals/cals, proteinCals/cals)
    }

    fun setAmount(amount: Long) {
        _calories.value = (_ingredient.value.product.energyKcal100g ?: 0.0) * amount / 100
        _carbs.value = (_ingredient.value.product.carbohydrates100g ?: 0.0) * amount / 100
        _fat.value = (_ingredient.value.product.fat100g ?: 0.0) * amount / 100
        _protein.value = (_ingredient.value.product.proteins100g ?: 0.0) * amount / 100
        _amount.value = amount
    }


}