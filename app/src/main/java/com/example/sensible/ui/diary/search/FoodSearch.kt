package com.example.sensible.ui.diary.search

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import com.example.sensible.R
import com.example.sensible.ui.components.SearchBar
import com.example.sensible.ui.components.SearchableFoodList
import com.example.sensible.ui.components.SensibleTopBar

@Composable
fun FoodSearch(
    popBackStack: () -> Unit,
    onScanClick: () -> Unit,
){
    val textState = remember { mutableStateOf(TextFieldValue("")) }
    Scaffold(
        topBar = {
            SensibleTopBar(
                title = stringResource(R.string.diary_search_name),
                navigationIcon = {
                    IconButton(onClick = popBackStack) {
                        Icon(Icons.Default.ArrowBack, null)
                    }})}
    ) {
        paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)){
            SearchBar(
                state = textState,
                onScanClick = onScanClick
            )
        }
    }
}