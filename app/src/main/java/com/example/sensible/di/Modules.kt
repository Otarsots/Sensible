package com.example.sensible.di

import androidx.room.Room
import com.example.sensible.data.AppDatabase
import com.example.sensible.data.repository.*
import com.example.sensible.ui.diary.list.DiaryEditorViewModel
import com.example.sensible.ui.diary.search.RecipeSearchViewModel
import com.example.sensible.ui.home.HomeViewModel
import com.example.sensible.ui.recipe.editor.RecipeEditorViewModel
import com.example.sensible.ui.recipe.list.RecipeListViewModel
import com.example.sensible.ui.recipe.search.ProductSearchViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val koinModule = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java,
            "database")
            .build()
    }

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
        ProductRepository(productDao = get())
    }

    factory {
        RecipeRepository(recipeDao = get())
    }

    factory {
        DiaryRepository(diaryDao = get())
    }


    viewModel {
        DiaryEditorViewModel(get())
    }

    viewModel {
        RecipeSearchViewModel(get())
    }

    viewModel {
        HomeViewModel()
    }

    viewModel { params ->
        RecipeEditorViewModel(get(), params.get())
    }

    viewModel {
        RecipeListViewModel()
    }

    viewModel {
        ProductSearchViewModel()
    }

}