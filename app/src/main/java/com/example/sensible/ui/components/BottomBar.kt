package com.example.sensible.ui.components

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.sensible.SensibleScreen
import com.example.sensible.ui.diary.FoodListPreview
import com.example.sensible.ui.diary.LightFoodListPreview
import com.example.sensible.ui.theme.SensibleTheme

@Composable
fun SensibleBottomBar(
    allScreens: List<SensibleScreen>,
    onTabSelected: (SensibleScreen) -> Unit,
    currentScreen: SensibleScreen,
) {
    val selected = remember { mutableStateOf(false) }

    BottomNavigation(
        backgroundColor = MaterialTheme.colors.surface,
        elevation = 32.dp
    ) {
        allScreens.forEach {screen ->
            BottomNavigationItem(
                icon = {Icon(painterResource(screen.icon),"")},
                modifier = Modifier.scale(if (currentScreen == screen) 1.2F else 1F),
                label = {Text(stringResource(screen.name))},
                selected = currentScreen == screen,
                onClick = {onTabSelected(screen)}
            )
        }
    }
}

@Preview
@Composable
fun BottomBarPreview(){
    SensibleTheme() {
        val allScreens = listOf(SensibleScreen.Home, SensibleScreen.Diary, SensibleScreen.Recipes)
        Scaffold(
            bottomBar = {SensibleBottomBar(
                allScreens = allScreens,
                onTabSelected = {},
                currentScreen = SensibleScreen.Diary)}
        ) {
            innerPadding ->
            Surface(Modifier.fillMaxSize().padding(innerPadding)) {
                searchableFoodListPreview()
            }
        }

    }
}