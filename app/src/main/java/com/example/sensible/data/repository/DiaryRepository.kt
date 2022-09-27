package com.example.sensible.data.repository

import com.example.sensible.data.dao.DiaryDao
import com.example.sensible.models.DiaryEntry
import com.example.sensible.models.Goal
import com.example.sensible.models.ListItem
import com.example.sensible.models.Nutrients
import kotlinx.coroutines.flow.Flow

class DiaryRepository(private val diaryDao: DiaryDao) {
    fun getNutrients(date: Long): Flow<Nutrients?> {
        return diaryDao.getNutrients(date)
    }

    suspend fun insertGoal(goal: Goal) {
        return diaryDao.insertGoal(goal)
    }

    suspend fun insertEntry(entry: DiaryEntry) {
        return diaryDao.insertEntry(entry)
    }

    suspend fun deleteEntry(date: Long, recipeId: Long) {
        return diaryDao.deleteEntry(date, recipeId)
    }

    fun getGoal(date: Long): Flow<Goal> {
        return diaryDao.getGoal(date)
    }

    fun getLastGoal(date: Long): Flow<Goal?> {
        return diaryDao.getLastGoal(date)
    }

    fun getDiaryList(date: Long): Flow<List<ListItem>> {
        return diaryDao.getDiaryList(date)
    }
}