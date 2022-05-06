package com.example.sensible

import android.Manifest
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
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.rememberNavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.sensible.ui.components.BarcodeScreen
import com.example.sensible.ui.components.CameraPreview
import com.example.sensible.ui.theme.SensibleTheme
import com.example.sensible.ui.components.SensibleBottomBar
import com.example.sensible.ui.diary.DiaryScreen
import com.example.sensible.ui.home.HomeScreen
import com.example.sensible.ui.recipe.RecipeScreen
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.systemuicontroller.rememberSystemUiController


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
            //Navigation
            val allScreens = SensibleScreen.values().toList()
            val navController = rememberNavController()
            val backstackEntry = navController.currentBackStackEntryAsState()
            val currentScreen = SensibleScreen.fromRoute(backstackEntry.value?.destination?.route)

            //Barcode Scanning
            val showDialog = mutableStateOf(false)
            val cameraPermissionState = rememberPermissionState(permission = Manifest.permission.CAMERA)

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
                        onClick = {
                            cameraPermissionState.launchPermissionRequest()
                            showDialog.value = true },
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
                if(showDialog.value) {
                    BarcodeScreen()
                }
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
            HomeScreen()
        }
        composable(SensibleScreen.Diary.name) {
            DiaryScreen()
        }
        composable(SensibleScreen.Recipes.name) {
            RecipeScreen()
        }
    }
}