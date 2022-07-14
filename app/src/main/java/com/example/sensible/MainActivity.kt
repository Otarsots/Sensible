package com.example.sensible

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.material.Scaffold
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.sensible.data.AppDatabase
import com.example.sensible.di.koinModule
import com.example.sensible.ui.components.BottomBar
import com.example.sensible.ui.theme.SensibleTheme
import com.example.sensible.ui.components.allScreens
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class MainActivity : ComponentActivity() {
    private val preferences: DataStore<Preferences> by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startKoin {
            androidContext(this@MainActivity)
            modules(koinModule)
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