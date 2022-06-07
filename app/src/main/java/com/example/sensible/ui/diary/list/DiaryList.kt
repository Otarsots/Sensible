package com.example.sensible.ui.diary

import androidx.compose.material.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.sensible.R
import com.example.sensible.SensibleScreen
import com.example.sensible.models.DiaryEntry
import com.example.sensible.models.Recipe
import com.example.sensible.ui.components.*
import com.example.sensible.ui.theme.SensibleTheme


@Composable
fun DiaryList(
    entries: List<DiaryEntry>,
    onEntryClick: (String) -> Unit = {},
    onAddClick: () -> Unit = {},
    onMenuClick: (String) -> Unit = {},
){
    Scaffold(
        topBar = {
            SensibleTopBar(
                title = stringResource(SensibleScreen.Diary.name),
                actions = { Icon(Icons.Default.MoreVert, "")}
            )
        },
        floatingActionButton = {
            SensibleActionButton(
                onClick = onAddClick
            )
        }
    ){

    }
}

@Preview
@Composable
fun DiaryPreview(){
    SensibleTheme(){
        val entry = remember{DiaryEntry(1, mutableListOf())}
        entry.foods.add(Recipe(emptyList(),100,1.0,"Griechische Reispfanne mit Spargel und ganz viel anderem Zeug"))
        entry.foods.add(Recipe(emptyList(),100,0.5,"Spargel"))
        val allScreens = listOf(SensibleScreen.Home, SensibleScreen.Diary, SensibleScreen.RecipeList)
        DiaryList(entries = listOf(entry))
    }
}
