package com.example.sensible.ui.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.sensible.R
import com.example.sensible.ui.components.SearchBar
import com.example.sensible.ui.components.SensibleTopBar


@Composable
fun HomeBody(
    onCircleClick: (String) -> Unit = {},
) {
    Scaffold(
        topBar = {
            SensibleTopBar(
                title = stringResource(R.string.home_name)
            )
        }
    ) {
            paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)){
        }
    }
}