package com.example.sensible.ui.diary.editor

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.sensible.R
import com.example.sensible.ui.components.SensibleTopBar

@Composable
fun DiaryEditor(
    diaryId: Int,
    popBackStack: () -> Unit,
){
    Scaffold(
        topBar = {
            SensibleTopBar(
                title = stringResource(R.string.diary_editor_name),
                navigationIcon = {
                    IconButton(onClick = popBackStack) {
                        Icon(Icons.Default.ArrowBack, null)
                    }
                },
                ) }
    ) {
        
    }
}