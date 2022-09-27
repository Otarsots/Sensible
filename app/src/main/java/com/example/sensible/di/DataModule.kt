package com.example.sensible.di

import androidx.room.Room
import com.example.sensible.data.AppDatabase
import com.example.sensible.data.repository.DiaryRepository
import com.example.sensible.data.repository.ProductRepository
import com.example.sensible.data.repository.RecipeRepository
import com.example.sensible.ui.diary.day.DiaryDayViewModel
import com.example.sensible.ui.diary.search.RecipeSearchViewModel
import com.example.sensible.ui.statistics.StatisticsScreenViewModel
import com.example.sensible.ui.product.editor.ProductEditorViewModel
import com.example.sensible.ui.recipe.editor.RecipeEditorViewModel
import com.example.sensible.ui.recipe.list.RecipeListViewModel
import com.example.sensible.ui.recipe.search.ProductSearchViewModel
import com.example.sensible.ui.settings.goal.GoalSettingsViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val dataModule = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java,
            "database")
                /* TODO working default database
            .createFromAsset("schemas/database.db")
            .fallbackToDestructiveMigration()
                 */
            .build()
    }
/*
    factory {
        androidContext().datastore
    }
 */
    factory {
        get<AppDatabase>().productDao
    }

    factory {
        get<AppDatabase>().recipeDao
    }

    factory {
        get<AppDatabase>().diaryDao
    }

    factory {
        ProductRepository(
            productDao = get(),
            productApi = get()
        )
    }

    factory {
        RecipeRepository(recipeDao = get())
    }

    factory {
        DiaryRepository(diaryDao = get())
    }


    viewModel {params ->
        DiaryDayViewModel(repository = get(), date = params.get())
    }

    viewModel {params ->
        RecipeSearchViewModel(
            recipeRepository = get(),
            diaryRepository = get(),
            date = params.get()
        )
    }

    viewModel {
        StatisticsScreenViewModel()
    }

    viewModel { params ->
        RecipeEditorViewModel(
            recipeRepository = get(),
            productRepository = get(),
            params.get()
        )
    }

    viewModel {
        RecipeListViewModel(get())
    }

    viewModel {
        ProductSearchViewModel(get())
    }

    viewModel {params ->
        ProductEditorViewModel(
            productRepository = get(),
            recipeRepository = get(),
            params.get()
        )
    }

    viewModel {
        GoalSettingsViewModel(
            repository = get()
        )
    }

}