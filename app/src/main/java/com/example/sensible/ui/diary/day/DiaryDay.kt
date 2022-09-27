package com.example.sensible.ui.diary.day

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.example.sensible.R
import com.example.sensible.SensibleScreen
import com.example.sensible.ui.components.*
import com.example.sensible.ui.theme.SensibleTheme
import com.example.sensible.ui.theme.extendedColors
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import org.koin.androidx.compose.getViewModel
import org.koin.core.parameter.parametersOf


@OptIn(ExperimentalMaterialApi::class, ExperimentalFoundationApi::class)
@Composable
fun DiaryDay(
//    entries: List<DiaryEntry>,
    date: Long,
    navToRecipeEditor: (Long) -> Unit = {},
    onAddClick: (Long) -> Unit = {},
    navToSettings: () -> Unit = {},
    navToDate: (Long) -> Unit,
    viewModel: DiaryDayViewModel = getViewModel{parametersOf(date)},
){
    val dateName by viewModel.dateString.collectAsState()
    val dialogState = rememberMaterialDialogState()
    MaterialDialog(
        dialogState = dialogState,
        buttons = {
            positiveButton("Ok")
            negativeButton("Cancel")
        }
    ) {
        datepicker { newDate ->
            navToDate(newDate.toEpochDay())
        }
    }

    Scaffold(
        topBar = {
            SensibleTopBar(
                title = stringResource(SensibleScreen.DiaryDay.name),
                actions = {
                    Icon(Icons.Default.DateRange, "",
                        Modifier
                            .padding(8.dp)
                            .clickable { dialogState.show() })
                    Icon(painterResource(R.drawable.ic_action_settings), "",
                        Modifier
                            .padding(8.dp)
                            .clickable { navToSettings() })
                }
            )
        },
        floatingActionButton = {
            SensibleActionButton(
                onClick = { onAddClick(date) }
            )
        }
    ){ padding ->
        
        Surface(
            modifier = Modifier
                .padding(padding)
                .padding(4.dp)
                .fillMaxSize(),
            color = MaterialTheme.colors.primary,
            shape = RoundedCornerShape(8.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(horizontalArrangement = Arrangement.SpaceEvenly) {
                    Text(
                        text = dateName,
                        style = MaterialTheme.typography.h6,
                        modifier = Modifier
                            .padding(horizontal = 8.dp, vertical = 8.dp),
                    )
                }
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Column {
                        Row(
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            AnimatedCircle(
                                modifier = Modifier
                                    .size(120.dp)
                                    .padding(16.dp),
                                useDivider = false,
                                strokeSize = 16.dp,
                                proportions = viewModel.getCarbsProgress(),
                                colors = listOf(MaterialTheme.extendedColors.carbs, Color.DarkGray)
                            )
                            AnimatedCircle(
                                modifier = Modifier
                                    .size(120.dp)
                                    .padding(16.dp),
                                useDivider = false,
                                strokeSize = 16.dp,
                                proportions = viewModel.getFatProgress(),
                                colors = listOf(MaterialTheme.extendedColors.fat, Color.DarkGray)
                            )
                            AnimatedCircle(
                                modifier = Modifier
                                    .size(120.dp)
                                    .padding(16.dp),
                                useDivider = false,
                                strokeSize = 16.dp,
                                proportions = viewModel.getProteinProgress(),
                                colors = listOf(
                                    MaterialTheme.extendedColors.protein,
                                    Color.DarkGray
                                )
                            )
                        }
                        val entries by viewModel.entries.collectAsState()
                        LazyColumn(
                            Modifier
                                .fillMaxSize(),
                            //.padding(4.dp),
                            contentPadding = PaddingValues(
                                top = 4.dp,
                                bottom = 70.dp),
                        ) {
                            items(
                                items = entries,
                                key = { it.id }) { entry ->
                                val dismissState = rememberDismissState()

                                SwipeToDismiss(
                                    modifier = Modifier
                                        .animateItemPlacement()
                                        .zIndex(if (dismissState.offset.value == 0f) 0f else 1f),
                                    state = dismissState,
                                    directions = setOf(DismissDirection.StartToEnd),
                                    dismissThresholds = { FractionalThreshold(0.7f) },
                                    background = { SwipeToDeleteBackground(dismissState) }
                                ) {
                                    FoodListItem(
                                        onItemClick = { navToRecipeEditor(entry.id) },
                                        title = entry.name.takeIf { it.isNotBlank() }
                                            ?: "unnamed",
                                        image = null,
                                        amount = entry.amount,
                                        calories =  entry.calories
                                    )
                                }

                                if (dismissState.targetValue != DismissValue.Default) {
                                    viewModel.deleteEntry(entry.id)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun DiaryPreview(){
    SensibleTheme(){

        //val allScreens = listOf(SensibleScreen.Home, SensibleScreen.DiaryEditor, SensibleScreen.RecipeList)
        //DiaryList(entries = listOf(entry)) { navController.navigate("${com.example.sensible.Screen.diaryEditor.name}?diaryId=$diaryId") }
    }
}
