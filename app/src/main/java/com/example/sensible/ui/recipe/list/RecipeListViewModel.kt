package com.example.sensible.ui.recipe.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sensible.data.repository.RecipeRepository
import com.example.sensible.models.Recipe
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch

class RecipeListViewModel(
    private val repository: RecipeRepository,
) : ViewModel() {
    private val _nameFilter = MutableStateFlow("")
    val nameFilter = _nameFilter.asStateFlow()

    val recipes: Flow<List<Recipe>> =
        repository.recipes.combine(nameFilter) { recipes, filter ->
            recipes.filter { recipe ->
                filter.lowercase() in recipe.name.lowercase()
            }
        }

    fun setNameFilter(name: String) {
        _nameFilter.value = name
    }

    fun addRecipe(onComplete: (Long) -> Unit) {
        viewModelScope.launch {
            val id = repository.insert(Recipe())
            onComplete(id)
        }
    }

    fun deleteRecipe(recipeId: Long) {
        viewModelScope.launch {
            repository.deleteRecipe(recipeId)
        }
    }
}