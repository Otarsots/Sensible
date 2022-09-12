package com.example.sensible.ui.diary.editor

import androidx.compose.foundation.clickable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.sensible.SensibleScreen
import com.example.sensible.models.DiaryEntry
import com.example.sensible.ui.components.*
import com.example.sensible.ui.theme.SensibleTheme
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import org.koin.androidx.compose.getViewModel
import org.koin.core.parameter.parametersOf


@Composable
fun DiaryEditor(
    entries: List<DiaryEntry>,
    date: Long,
    navToRecipeEditor: (Long) -> Unit = {},
    onAddClick: () -> Unit = {},
    onMenuClick: (String) -> Unit = {},
    navToDate: (Long) -> Unit,
    viewModel: DiaryEditorViewModel = getViewModel{parametersOf(date)},
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
                title = stringResource(SensibleScreen.DiaryEditor.name),
                actions = {
                    Icon(Icons.Default.DateRange, "",
                        Modifier
                            .padding(16.dp)
                            .clickable { dialogState.show() })
                    Icon(Icons.Default.MoreVert, "")
                }
            )
        },
        floatingActionButton = {
            SensibleActionButton(
                onClick = onAddClick
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

                }
            }
        }
    }
}

@Preview
@Composable
fun DiaryPreview(){
    SensibleTheme(){

        val allScreens = listOf(SensibleScreen.Home, SensibleScreen.DiaryEditor, SensibleScreen.RecipeList)
        //DiaryList(entries = listOf(entry)) { navController.navigate("${com.example.sensible.Screen.diaryEditor.name}?diaryId=$diaryId") }
    }
}
