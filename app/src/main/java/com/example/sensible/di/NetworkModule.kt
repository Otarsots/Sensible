package com.example.sensible.di

import com.example.sensible.models.BarcodeRequestResult
import com.example.sensible.models.Product
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.koin.dsl.module
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

val networkModule = module {
    factory { provideProductApi(get()) }
    single { provideRetrofit() }
}

fun provideRetrofit(): Retrofit {
    return Retrofit.Builder().baseUrl("https://world.openfoodfacts.org/")
        .addConverterFactory(GsonConverterFactory.create()).build()
}

fun provideProductApi(retrofit: Retrofit): OpenFoodFactsApi = retrofit.create(
    OpenFoodFactsApi::class.java)

interface OpenFoodFactsApi {
    @GET("https://world.openfoodfacts.org/api/v0/product/{code}.json?" +
            "fields=product_name,code,energy-kcal_100g,carbohydrates_100g,fat_100g,proteins_100g,image_url")
    suspend fun getRequestResult(@Path("code") code: Long) : Response<BarcodeRequestResult>
    suspend fun getProduct(barcode: Long) : Flow<Product?> {
        return flow {
            emit(getRequestResult(barcode).body()?.product)
        }
    }

}