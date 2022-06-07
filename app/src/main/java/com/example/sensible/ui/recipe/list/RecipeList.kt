package com.example.sensible.ui.recipe

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.sensible.R
import com.example.sensible.ui.components.SensibleTopBar


@Composable
fun RecipeList() {
    Scaffold(
        topBar = {
            SensibleTopBar(
                title = stringResource(R.string.recipes_name)
            )
        }
    ) {
            paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)){
        }
    }
}

