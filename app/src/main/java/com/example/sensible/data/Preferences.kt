package com.example.sensible.data

import android.content.Context
import androidx.annotation.StringRes
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import com.example.sensible.R

val Context.datastore: DataStore<Preferences> by preferencesDataStore(name = "settings")


enum class ColorTheme(
    @StringRes val themeName: Int
) {
    FollowSystem(R.string.app_theme_follow_system),
    White(R.string.app_theme_light),
    Black(R.string.app_theme_dark)
}

sealed class AppPrefs<T>(val key: Preferences.Key<T>, val defaultValue: T) {
    object IsFirstRun : AppPrefs<Boolean>(
        key = booleanPreferencesKey("isFirstRun"),
        defaultValue = false
    )

    object CalorieGoal : AppPrefs<Int>(
        key = intPreferencesKey("calorieGoal"),
        defaultValue = -1
    )

    object ProteinGoal : AppPrefs<Int>(
        key = intPreferencesKey("proteinGoal"),
        defaultValue = -1
    )

    object CarbGoal : AppPrefs<Double>(
        key = doublePreferencesKey("carbGoal"),
        defaultValue = 0.0
    )

    object FatGoal : AppPrefs<Double>(
        key = doublePreferencesKey("fatGoal"),
        defaultValue = 0.0
    )

    object AppTheme : AppPrefs<String>(
        key = stringPreferencesKey("appTheme"),
        defaultValue = ColorTheme.FollowSystem.name
    )

    object UpdateRoutineAfterWorkout : AppPrefs<Boolean>(
        key = booleanPreferencesKey("updateRoutineAfterWorkout"),
        defaultValue = false
    )
}

suspend fun DataStore<Preferences>.resetAppSettings() {
    edit {
        //it[AppPrefs.ShowBottomNavLabels.key] = AppPrefs.ShowBottomNavLabels.defaultValue
        it[AppPrefs.IsFirstRun.key] = AppPrefs.IsFirstRun.defaultValue
        it[AppPrefs.AppTheme.key] = AppPrefs.AppTheme.defaultValue
        it[AppPrefs.UpdateRoutineAfterWorkout.key] = AppPrefs.UpdateRoutineAfterWorkout.defaultValue
    }
}
