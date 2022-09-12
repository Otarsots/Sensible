package com.example.sensible.ui.diary.calendar

import androidx.compose.material.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.sensible.ui.theme.SensibleTheme
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState


// For displaying preview in
// the Android Studio IDE emulator
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SensibleTheme() {
        val navToDate: (Long) -> Unit = {}
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
                // Do stuff with java.time.LocalDate object which is passed in
            }
        }

/* This should be called in an onClick or an Effect */
        Button(onClick = {dialogState.show()}) {
            
        }
    }
}
