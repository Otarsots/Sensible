package com.example.sensible.ui.recipe.list

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.example.sensible.R
import com.example.sensible.models.Recipe
import com.example.sensible.ui.components.*
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
            modifier = Modifier.padding(paddingValues)
        )
    }
}

@OptIn(ExperimentalMaterialApi::class, ExperimentalFoundationApi::class)
@Composable
fun RecipeListContent(
    recipes: List<Recipe>,
    viewModel: RecipeListViewModel,
    navToRecipeEditor: (Long) -> Unit,
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
                val dismissState = rememberDismissState()

                SwipeToDismiss(
                    modifier = Modifier
                        .animateItemPlacement()
                        .zIndex(if (dismissState.offset.value == 0f) 0f else 1f),
                    state = dismissState,
                    directions = setOf(DismissDirection.StartToEnd),
                    dismissThresholds = { FractionalThreshold(0.7f) },
                    background = { SwipeToDeleteBackground(dismissState) }
                ) {
                    FoodListItem(
                        onItemClick = { navToRecipeEditor(recipe.recipeId) },
                        title = recipe.name.takeIf { it.isNotBlank() }
                            ?: "unnamed",
                    )
                }
                if (dismissState.targetValue != DismissValue.Default) {
                    viewModel.deleteRecipe(recipe.recipeId)
                }
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