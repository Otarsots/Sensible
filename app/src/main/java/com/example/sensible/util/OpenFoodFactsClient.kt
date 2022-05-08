package com.example.sensible.util

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.sensible.models.Product
import com.example.sensible.models.ProductResult
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Path

interface OpenFoodFactsApi {
    @GET("/api/v0/product/{code}")
    suspend fun getProduct(@Path("code") code: Long) : Response<ProductResult>
}

object RetrofitHelper {

    private const val baseUrl = "https://world.openfoodfacts.org/"

    fun getInstance(): Retrofit {
        return Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            // we need to add converter factory to
            // convert JSON object to Java object
            .build()
    }
}

suspend fun getProductData(barcode: Long): Product? {
    val openFoodFactsApi = RetrofitHelper.getInstance().create(OpenFoodFactsApi::class.java)
    val result = openFoodFactsApi.getProduct(barcode)
    return result.body()?.product
}

@Preview(showBackground = true)
@Composable
fun PreviewFood(){
    val coroutineScope = rememberCoroutineScope()
    val text = remember{mutableStateOf("")}
    val getProductOnClick: () -> Unit = {
        coroutineScope.launch {
            text.value = getProductData(737628064502)?.genericName.toString()
        }
    }

    Row {
        Button(onClick = getProductOnClick) {
        }
        val scroll = rememberScrollState(0)
        val scroll2 = rememberScrollState(0)
        Text(text.value,
            Modifier
                .horizontalScroll(scroll)
                .verticalScroll(scroll2))
    }

}