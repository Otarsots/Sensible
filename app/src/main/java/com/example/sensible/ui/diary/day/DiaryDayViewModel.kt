package com.example.sensible.ui.diary.day

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sensible.data.repository.DiaryRepository
import com.example.sensible.models.Goal
import com.example.sensible.models.ListItem
import com.example.sensible.models.Nutrients
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate

class DiaryDayViewModel(
    private val repository: DiaryRepository,
    private val date: Long
) : ViewModel() {
    private val _dateString = MutableStateFlow("")
    val dateString = _dateString.asStateFlow()

    private val _nutrients = MutableStateFlow(Nutrients())
    val nutrients = _nutrients.asStateFlow()

    private val _goal = MutableStateFlow(Goal())
    val goal = _goal.asStateFlow()

    private var _entries = MutableStateFlow<List<ListItem>>(emptyList())
    val entries = _entries.asStateFlow()

    init{
        viewModelScope.launch {
            launch {
                val day = LocalDate.ofEpochDay(date)
                _dateString.value = day.toString()
            }
            launch {
                repository.getLastGoal(date).collect{ result ->
                    result?.let {
                        _goal.value = it
                    }
                }
            }
            launch {
                repository.getNutrients(date).collect{ res ->
                    res?.let {
                        _nutrients.value = it
                    }
                }
            }
            launch {
                repository.getDiaryList(date).collect{
                    _entries.value = it
                }
            }
        }
    }

    fun getCalorieProgress(): List<Float> {
        if(goal.value.caloriesGoal == 0.0) return listOf(0f, 1f)
        val percentage = (nutrients.value.calories/goal.value.caloriesGoal).toFloat()
        return listOf(percentage,1-percentage)
    }
    fun getCarbsProgress(): List<Float> {
        if(goal.value.carbsGoal == 0.0) return listOf(0f, 1f)
        val percentage = (nutrients.value.carbs/goal.value.carbsGoal).toFloat()
        return listOf(percentage,1-percentage)
    }
    fun getFatProgress(): List<Float> {
        if(goal.value.fatsGoal == 0.0) return listOf(0f, 1f)
        val percentage = (nutrients.value.fat/goal.value.fatsGoal).toFloat()
        return listOf(percentage,1-percentage)
    }
    fun getProteinProgress(): List<Float> {
        if(goal.value.proteinGoal == 0.0) return listOf(0f, 1f)
        val percentage = (nutrients.value.protein/goal.value.proteinGoal).toFloat()
        return listOf(percentage,1-percentage)
    }
    fun deleteEntry(recipeId: Long) {
        viewModelScope.launch{
            repository.deleteEntry(date, recipeId)
        }
    }
}