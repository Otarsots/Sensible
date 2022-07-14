package com.example.sensible.ui.diary.list

import androidx.compose.foundation.clickable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
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


@Composable
fun DiaryEditor(
    entries: List<DiaryEntry>,
    diaryId: Long,
    navToRecipeEditor: (Long) -> Unit = {},
    onAddClick: () -> Unit = {},
    onMenuClick: (String) -> Unit = {},
    navToDate: (Long) -> Unit,
    viewModel: DiaryEditorViewModel = getViewModel(),
){
    val dialogState = rememberMaterialDialogState()
    MaterialDialog(
        dialogState = dialogState,
        buttons = {
            positiveButton("Ok")
            negativeButton("Cancel")
        }
    ) {
        datepicker { date ->
            navToDate(date.toEpochDay())
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
    ){ paddingValues ->
        Box(Modifier.padding(paddingValues))
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
