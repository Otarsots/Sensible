package com.example.sensible.ui.settings.goal

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sensible.data.repository.DiaryRepository
import com.example.sensible.models.Goal
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate

class GoalSettingsViewModel(
    private val repository: DiaryRepository,
) : ViewModel() {
    private val _goal = MutableStateFlow(Goal())
    val goal = _goal.asStateFlow()
    private val _cals = MutableStateFlow("0")
    val cals = _cals.asStateFlow()

    private val _carbs = MutableStateFlow("0")
    val carbs = _carbs.asStateFlow()

    private val _fat = MutableStateFlow("0")
    val fat = _fat.asStateFlow()

    private val _protein = MutableStateFlow("0")
    val protein = _protein.asStateFlow()
    init {
        viewModelScope.launch {

            repository.getLastGoal(LocalDate.now().toEpochDay()).collect { res ->
                res?.let {
                    _goal.value = it.copy(date = LocalDate.now().toEpochDay())
                    _cals.value = it.caloriesGoal.toLong().toString()
                    _carbs.value = it.carbsGoal.toLong().toString()
                    _fat.value = it.fatsGoal.toLong().toString()
                    _protein.value = it.proteinGoal.toLong().toString()
                }
            }
        }
    }

    fun setCalories(value: String) {
        _cals.value = value
    }

    fun setCarbs(value: String) {
        _carbs.value = value
    }

    fun setFat(value: String) {
        _fat.value = value
    }

    fun setProtein(value: String) {
        _protein.value = value
    }

    fun updateCalories(value: String) {
        viewModelScope.launch {
            repository.insertGoal(goal.value.copy(caloriesGoal = value.toDoubleOrNull()?:0.0))
        }
    }

    fun updateCarbs(value: String) {
        viewModelScope.launch {
            repository.insertGoal(goal.value.copy(carbsGoal = value.toDoubleOrNull()?:0.0))
        }
    }

    fun updateFat(value: String) {
        viewModelScope.launch {
            repository.insertGoal(goal.value.copy(fatsGoal = value.toDoubleOrNull()?:0.0))
        }
    }

    fun updateProtein(value: String) {
        viewModelScope.launch {
            repository.insertGoal(goal.value.copy(proteinGoal = value.toDoubleOrNull()?:0.0))
        }
    }
}