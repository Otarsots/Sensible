package com.example.sensible

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.sensible.data.AppDatabase
import com.example.sensible.di.dataModule
import com.example.sensible.di.networkModule
import com.example.sensible.ui.components.BottomBar
import com.example.sensible.ui.components.allScreens
import com.example.sensible.ui.theme.SensibleTheme
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startKoin {
            androidContext(this@MainActivity)
            modules(dataModule, networkModule)
        }
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
            val database = AppDatabase

            //Navigation
            val navController = rememberNavController()
            val backstackEntry = navController.currentBackStackEntryAsState()

            Scaffold(
                bottomBar = {
                    val navBackStackEntry by navController.currentBackStackEntryAsState()
                    val isCurrentDestinationHomeTab =
                        navBackStackEntry
                            ?.destination
                            ?.route
                            ?.substringBefore("?") in allScreens.map { it.route }
                    if (isCurrentDestinationHomeTab) {
                        BottomBar(
                            navController = navController,
                        )
                    }
                }
            ) { paddingValues ->
                Surface(
                    modifier = Modifier.padding(paddingValues),
                    elevation = 2.dp){
                    SensibleNavHost(navController)
                }
            }
        }
    }
}


@Preview
@Composable
fun BottomBarPreview(){
    SensibleApp()
}