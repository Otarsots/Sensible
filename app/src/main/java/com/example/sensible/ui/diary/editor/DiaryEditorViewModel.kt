package com.example.sensible.ui.diary.editor

import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sensible.data.repository.DiaryRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.time.LocalDate

class DiaryEditorViewModel(
    private val repository: DiaryRepository,
    private val date: Long
) : ViewModel() {
    private val _dateString = MutableStateFlow("")
    val dateString = _dateString.asStateFlow()
    init{
        viewModelScope.launch {
            val template = SimpleDateFormat.getDateInstance()
            val day = LocalDate.ofEpochDay(date)
            _dateString.value = day.toString()
            println("done")
        }
    }
}