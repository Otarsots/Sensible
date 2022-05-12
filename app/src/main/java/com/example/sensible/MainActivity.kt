package com.example.sensible

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.material.Scaffold
import androidx.navigation.compose.rememberNavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.sensible.data.ProductRoomDatabase
import com.example.sensible.ui.theme.SensibleTheme
import com.example.sensible.ui.components.SensibleBottomBar
import com.google.accompanist.permissions.ExperimentalPermissionsApi


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SensibleApp()
        }
    }
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun SensibleApp(){
    SensibleTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            //Database
            val database = ProductRoomDatabase

            //Navigation
            val allScreens = SensibleScreen.values().toList()
            val navController = rememberNavController()
            val backstackEntry = navController.currentBackStackEntryAsState()
            val currentScreen = SensibleScreen.fromRoute(backstackEntry.value?.destination?.route)

            //Barcode Scanning
            val showDialog = remember{mutableStateOf(false)}


            Scaffold(
                bottomBar = {
                    SensibleBottomBar(
                        allScreens = allScreens,
                        onTabSelected = {screen ->
                            navController.navigate(screen.name)
                        },
                        currentScreen = currentScreen)
                },
                floatingActionButtonPosition = FabPosition.End,
                floatingActionButton = {
                    FloatingActionButton(
                        onClick = {navController.navigate("scanner")},
                        content = {
                            Icon(
                                Icons.Filled.Favorite,
                                contentDescription = null,
                            )
                        },
                        backgroundColor = MaterialTheme.colors.secondary,
                        contentColor = MaterialTheme.colors.onSecondary,
                    )
                },
            ) {
                RallyNavHost(navController)

            }
        }
    }
}

@Composable
fun RallyNavHost(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(
        navController = navController,
        startDestination = SensibleScreen.Home.name,
        modifier = modifier
    ) {
        composable(SensibleScreen.Home.name) {
            HomeBody(

            )
        }
        composable(SensibleScreen.Diary.name) {
            DiaryBody(

            )
        }
        composable(SensibleScreen.Recipes.name) {
            RecipeBody(

            )
        }
    }
}