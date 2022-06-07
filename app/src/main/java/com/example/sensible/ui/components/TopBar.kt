package com.example.sensible.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.android.datatransport.runtime.dagger.Component

@Composable
fun SensibleTopBar(
    modifier: Modifier = Modifier,
    title: String,
    navigationIcon: (@Composable () -> Unit)? = null,
    actions: @Composable RowScope.() -> Unit = {},
) {
    TopAppBar(
        modifier = modifier,
        //elevation = 8.dp,
        //backgroundColor = MaterialTheme.colors.surface
    ) {
        Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
            Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                if (navigationIcon != null) navigationIcon()
                Spacer(Modifier.weight(1f))
                actions()
            }
            Text(title, style = MaterialTheme.typography.h6)
        }
    }
}
