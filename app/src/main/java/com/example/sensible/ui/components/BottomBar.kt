package com.example.sensible.ui.components

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.*
import com.example.sensible.SensibleScreen

@Composable
fun SensibleBottomBar(
    allScreens: List<SensibleScreen>,
    onTabSelected: (SensibleScreen) -> Unit,
    currentScreen: SensibleScreen,
) {
    BottomNavigation(
        backgroundColor = MaterialTheme.colors.primary,
        contentColor = MaterialTheme.colors.onPrimary,
    ) {
        allScreens.forEach {screen ->
            BottomNavigationItem(
                icon = {screen.icon},
                label = {screen.name},
                selected = currentScreen == screen,
                onClick = {onTabSelected(screen)}
            )
        }
    }
}