package com.example.sensible.ui.settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.sensible.R
import com.example.sensible.ui.components.SensibleTopBar

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SettingsMenu(
    popBackStack: () -> Unit = {},
    navToGoalSettings: () -> Unit = {}
){
    Scaffold(
        topBar = {
            SensibleTopBar(
                title = stringResource(R.string.settings_name),
                navigationIcon = {
                    IconButton(onClick = popBackStack) {
                        Icon(Icons.Default.ArrowBack, stringResource(R.string.btn_pop_back))
                    }
                })
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .scrollable(
                    orientation = Orientation.Vertical,
                    state = rememberScrollState()
                )
        ) {
            ListItem(
                modifier = Modifier.clickable(onClick = navToGoalSettings),
                text = { Text(stringResource(R.string.settings_goal_name)) },
                icon = { Icon(painterResource(R.drawable.ic_action_goal), null) }
            )
        }
    }
}