package com.example.sensible.ui.recipe.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sensible.data.repository.ProductRepository
import com.example.sensible.models.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import java.util.*


class ProductSearchViewModel(
    private val repository: ProductRepository
): ViewModel() {
    private val _nameFilter = MutableStateFlow("")
    val nameFilter = _nameFilter.asStateFlow()

    fun setNameFilter(filter: String) {
        _nameFilter.value = filter
    }

    val products = repository.products.combine(_nameFilter) { products, nameFilter ->
        products.filter {
            it.productName.lowercase(Locale.getDefault())
                .contains(nameFilter.lowercase(Locale.getDefault()))
        }
    }

    fun delete(product: Product) {
        viewModelScope.launch {
            repository.delete(product)
        }
    }
}