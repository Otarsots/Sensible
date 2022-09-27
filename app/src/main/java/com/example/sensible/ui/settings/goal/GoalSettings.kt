package com.example.sensible.ui.settings.goal

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.sensible.R
import com.example.sensible.ui.components.SensibleTopBar
import org.koin.androidx.compose.getViewModel

@Composable
fun GoalSettings(
    popBackStack: () -> Unit = {},
    viewModel: GoalSettingsViewModel = getViewModel()
) {
    val cals by viewModel.cals.collectAsState()
    val carbs by viewModel.carbs.collectAsState()
    val fat by viewModel.fat.collectAsState()
    val protein by viewModel.protein.collectAsState()
    LaunchedEffect(cals) {
        viewModel.updateCalories(cals)
    }
    LaunchedEffect(carbs) {
        viewModel.updateCarbs(carbs)
    }
    LaunchedEffect(fat) {
        viewModel.updateFat(fat)
    }
    LaunchedEffect(protein) {
        viewModel.updateProtein(protein)
    }

    Scaffold(
        topBar = {SensibleTopBar(
            title = stringResource(R.string.settings_goal_name),
            navigationIcon = {
                IconButton(onClick = popBackStack) {
                    Icon(Icons.Default.ArrowBack, stringResource(R.string.btn_pop_back))
                }
            })}
    ) {paddingValues ->
        Column(
            Modifier.padding(paddingValues)
        ) {
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, start = 30.dp, end = 30.dp),
                value = cals,
                label = { Text("Calories") },
                onValueChange = { viewModel.setCalories(it) },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                singleLine = true,
            )
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, start = 30.dp, end = 30.dp),
                value = carbs,
                label = { Text("Carbohydrates") },
                onValueChange = { viewModel.setCarbs(it) },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                singleLine = true,
            )
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, start = 30.dp, end = 30.dp),
                value = fat,
                label = { Text("Fat") },
                onValueChange = { viewModel.setFat(it) },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                singleLine = true,
            )
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, start = 30.dp, end = 30.dp),
                value = protein,
                label = { Text("Protein") },
                onValueChange = { viewModel.setProtein(it) },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                singleLine = true,
            )
        }
    }
}
