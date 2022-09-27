package com.example.sensible.ui.diary.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sensible.data.repository.DiaryRepository
import com.example.sensible.data.repository.RecipeRepository
import com.example.sensible.models.DiaryEntry
import com.example.sensible.models.Recipe
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch

class RecipeSearchViewModel(
    private val recipeRepository: RecipeRepository,
    private val diaryRepository: DiaryRepository,
    private val date: Long,
) : ViewModel() {
    private val _nameFilter = MutableStateFlow("")
    val nameFilter = _nameFilter.asStateFlow()

    val recipes: Flow<List<Recipe>> =
        recipeRepository.recipes.combine(nameFilter) { recipes, filter ->
            recipes.filter { recipe ->
                filter.lowercase() in recipe.name.lowercase()
            }
        }

    fun setNameFilter(name: String) {
        _nameFilter.value = name
    }

    fun addEntry(recipeId: Long, action: () -> Unit) {
        viewModelScope.launch {
            diaryRepository.insertEntry(DiaryEntry(date = date, recipeId = recipeId))
        }.invokeOnCompletion {
            action.invoke()
        }
    }
}