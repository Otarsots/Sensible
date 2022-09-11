package com.example.sensible.ui.recipe.list

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.sensible.R
import com.example.sensible.models.Recipe
import com.example.sensible.ui.components.FoodListItem
import com.example.sensible.ui.components.SearchBar
import com.example.sensible.ui.components.SensibleActionButton
import com.example.sensible.ui.components.SensibleTopBar
import com.example.sensible.ui.recipe.list.RecipeListViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun RecipeList(
    navToRecipeEditor: (Long) -> Unit,
    viewModel: RecipeListViewModel = getViewModel()
) {
    Scaffold(
        topBar = {
            SensibleTopBar(
                title = stringResource(R.string.recipes_name)
            )
        },
        floatingActionButton =
        {
            SensibleActionButton (
                onClick = {
                    viewModel.addRecipe(
                        onComplete = { id ->
                            navToRecipeEditor(id)
                        }
                    )
                },
            )
        }
    ) { paddingValues ->
        val recipes by viewModel.recipes.collectAsState(null)
        RecipeListContent(
            recipes = recipes ?: emptyList(),
            viewModel = viewModel,
            navToRecipeEditor = navToRecipeEditor,
            modifier = Modifier.padding(paddingValues))
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun RecipeListContent(
    recipes: List<Recipe>,
    viewModel: RecipeListViewModel,
    navToRecipeEditor: (Long) -> Unit,
    modifier: Modifier = Modifier,
) {
    val scope = rememberCoroutineScope()

    LazyColumn(
        modifier
            .fillMaxHeight()) {
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
                onItemClick = { navToRecipeEditor(recipe.recipeId) },
                title = recipe.name.takeIf { it.isNotBlank() }
                    ?: "TODO",
                calories = recipe.calories,
            )
        }
        item {
            // Fix FAB overlap
            Box(Modifier.height(72.dp)) {}
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