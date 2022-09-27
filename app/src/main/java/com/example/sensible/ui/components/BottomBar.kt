package com.example.sensible.ui.components

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.sensible.SensibleScreen


val allScreens = listOf(
    SensibleScreen.DiaryDay,
    SensibleScreen.RecipeList,
    SensibleScreen.Statistics
)

@Composable
fun BottomBar(
    navController: NavController,
    showLabels: Boolean = true,
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route?.substringBefore("?")

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
                modifier = Modifier.scale(if (item.route == currentRoute) 1.0F else 0.9F),
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