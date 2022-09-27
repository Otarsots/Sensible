package com.example.sensible.ui.diary.search

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.example.sensible.R
import com.example.sensible.models.Recipe
import com.example.sensible.ui.components.*
import com.example.sensible.ui.recipe.list.RecipeListViewModel
import org.koin.androidx.compose.getViewModel
import org.koin.core.parameter.parametersOf


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun RecipeSearch(
    date: Long,
    popBackStack: () -> Unit,
    viewModel: RecipeSearchViewModel = getViewModel{parametersOf(date)}
) {
    Scaffold(
        topBar = {
            SensibleTopBar(
                navigationIcon = {
                    IconButton(onClick = popBackStack) {
                        Icon(Icons.Default.ArrowBack, stringResource(R.string.btn_pop_back))
                    }
                },
                title = stringResource(R.string.diary_search_name)
            )
        }
    ) { paddingValues ->
        val recipes by viewModel.recipes.collectAsState(null)
        RecipeSearchContent(
            recipes = recipes ?: emptyList(),
            viewModel = viewModel,
            onClick = popBackStack,
            modifier = Modifier.padding(paddingValues))
    }
}

@OptIn(ExperimentalMaterialApi::class, ExperimentalFoundationApi::class)
@Composable
fun RecipeSearchContent(
    recipes: List<Recipe>,
    viewModel: RecipeSearchViewModel,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val scope = rememberCoroutineScope()

    Surface(
        modifier = Modifier
            .padding(4.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        LazyColumn(
            modifier
                .fillMaxHeight()
        ) {
            item {
                val nameFilter by viewModel.nameFilter.collectAsState()
                SearchBar(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    value = nameFilter,
                    onValueChange = viewModel::setNameFilter
                )
            }

            items(items = recipes, key = { it.recipeId }) { recipe ->
                FoodListItem(
                    onItemClick = { viewModel.addEntry(recipe.recipeId) { onClick() } },
                    title = recipe.name.takeIf { it.isNotBlank() }
                        ?: "unnamed",
                )
            }
            item {
                // Fix FAB overlap
                Box(Modifier.height(72.dp)) {}
            }
        }
    }
}
/*
FoodListItem(
                onItemClick = { navToRecipeEditor(recipe.recipeId) },
                title = recipe.name.takeIf { it.isNotBlank() }
                    ?: "TODO",
                calories = recipe.calories,
            )
 */