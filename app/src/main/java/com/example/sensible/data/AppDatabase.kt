package com.example.sensible.data

import android.content.Context
import androidx.room.*
import com.example.sensible.data.dao.DiaryDao
import com.example.sensible.data.dao.ProductDao
import com.example.sensible.data.dao.RecipeDao
import com.example.sensible.models.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken




@Database(entities = [Product::class, Goal::class, Recipe::class, RecipeProductCrossRef::class, DiaryEntry::class],
    views = [Ingredient::class, RecipeNutrients::class, DateNutrients::class]
    , version = 1)
abstract class AppDatabase: RoomDatabase() {

    abstract val productDao: ProductDao
    abstract val recipeDao: RecipeDao
    abstract val diaryDao: DiaryDao

    companion object {

        private var INSTANCE: AppDatabase? = null

    }
}