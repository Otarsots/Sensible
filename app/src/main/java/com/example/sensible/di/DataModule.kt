package com.example.sensible.di

import androidx.room.Room
import com.example.sensible.data.AppDatabase
import com.example.sensible.data.datastore
import com.example.sensible.data.repository.*
import com.example.sensible.ui.diary.list.DiaryEditorViewModel
import com.example.sensible.ui.diary.search.RecipeSearchViewModel
import com.example.sensible.ui.home.HomeViewModel
import com.example.sensible.ui.recipe.editor.RecipeEditorViewModel
import com.example.sensible.ui.recipe.list.RecipeListViewModel
import com.example.sensible.ui.recipe.search.ProductSearchViewModel
import com.example.sensible.util.OpenFoodFactsApi
import com.example.sensible.util.RetrofitHelper
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val dataModule = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java,
            "database")
            .build()
    }

    factory {
        androidContext().datastore
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
        ProductRepository(productDao = get(), productApi = get())
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
        RecipeEditorViewModel(get(), get(), params.get())
    }

    viewModel {
        RecipeListViewModel(get())
    }

    viewModel {
        ProductSearchViewModel(get())
    }

}