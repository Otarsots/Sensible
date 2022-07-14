package com.example.sensible.ui.recipe.editor

import androidx.lifecycle.ViewModel
import com.example.sensible.data.repository.RecipeRepository


class RecipeEditorViewModel(
    private val repository: RecipeRepository,
    private val recipeId: Long,
): ViewModel() {
}