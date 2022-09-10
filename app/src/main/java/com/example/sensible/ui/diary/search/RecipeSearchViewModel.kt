package com.example.sensible.ui.diary.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sensible.data.repository.ProductRepository
import com.example.sensible.data.repository.RecipeRepository
import com.example.sensible.models.Product
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class RecipeSearchViewModel(
    private val repository: RecipeRepository,
) : ViewModel() {
    /*
    fun scan(onComplete: () -> Unit) {
        viewModelScope.launch {
            val product = getProductData(productId)
            if (product != null) {
                repository.insert(product)
            }
        }.invokeOnCompletion {
            onComplete()
        }
    }

     */
}
