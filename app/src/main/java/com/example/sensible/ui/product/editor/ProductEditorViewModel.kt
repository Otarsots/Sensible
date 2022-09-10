package com.example.sensible.ui.product.editor

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sensible.data.repository.ProductRepository
import com.example.sensible.data.repository.RecipeRepository
import com.example.sensible.models.Product
import com.example.sensible.models.Recipe
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class ProductEditorViewModel(
    private val productRepository: ProductRepository,
    private val productId: Long,
): ViewModel() {
    private val _productFlow = productRepository.getProduct(productId)
    var product: Product? = null
    var name by mutableStateOf("")

    init {
        viewModelScope.launch {
                _productFlow.collect {value -> product = value}
            }
    }
    fun productName():String {
        return product?.productName ?: ""
    }
}