package com.example.sensible.ui.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.sensible.R
import com.example.sensible.Screen
import com.example.sensible.SensibleScreen
import com.example.sensible.ui.theme.SensibleTheme


val allScreens = listOf(
    SensibleScreen.Home,
    SensibleScreen.Diary,
    SensibleScreen.RecipeList
)

@Composable
fun BottomBar(
    navController: NavController,
    showLabels: Boolean = true,
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route

    BottomNavigation {
        for (item in allScreens) {
            BottomNavigationItem(
                icon = { Icon(painterResource(item.icon), null) },
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                label = (@Composable { Text(stringResource(item.name)) }).takeIf { showLabels },
                selected = item.route == currentRoute,
                modifier = Modifier.scale(if (item.route == currentRoute) 1.2F else 1F),
            )
        }
    }
}

/*
@Composable
fun SensibleBottomBar(
    onTabSelected: (String) -> Unit
) {
    val selected = remember { mutableStateOf(false) }

    BottomNavigation(
        backgroundColor =
        if(MaterialTheme.colors.isLight)
            MaterialTheme.colors.primary
        else
            MaterialTheme.colors.surface,
        elevation = 8.dp
    ) {
        allScreens.forEach {screen ->
            BottomNavigationItem(
                icon = {Icon(painterResource(screen.icon),"")},
                modifier = Modifier.scale(if (currentScreen.name == screen.route) 1.2F else 1F),
                label = {Text(stringResource(screen.name))},
                selected = currentScreen.name == screen.route,
                onClick = {onTabSelected(screen.route)}
            )
        }
    }
}
*/
/*
@Preview
@Composable
fun BottomBarPreview(){
    SensibleTheme(darkTheme = true) {
        Scaffold(
            topBar = { SensibleTopBar(title = "Diary") },
            bottomBar = {BottomBar(
                navController = {}
            )}
        ) {
            innerPadding ->
            Surface(
                Modifier
                    .fillMaxSize()
                    .padding(innerPadding)) {
                searchableFoodListPreview()
            }
        }

    }
}
*/