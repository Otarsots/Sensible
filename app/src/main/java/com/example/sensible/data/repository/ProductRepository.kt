package com.example.sensible.data.repository

import com.example.sensible.data.dao.ProductDao
import com.example.sensible.di.OpenFoodFactsApi
import com.example.sensible.models.Product
import kotlinx.coroutines.flow.*

class ProductRepository(private val productDao: ProductDao,
                        private val productApi: OpenFoodFactsApi
                        ) {

    val products = productDao.getProducts()

    suspend fun insert(product: Product) {
        return productDao.insert(product)
    }

    suspend fun update(product: Product) {
        return productDao.update(product)
    }

    suspend fun delete(product: Product) {
        return productDao.delete(product)
    }

    fun getProduct(id: Long): Flow<Product> {
        return productDao.getProduct(id)
    }

    suspend fun fetchProduct(id: Long): Product? {
        val res = productApi.getRequestResult(id)
        val product = res.body()?.product
        if (product != null) {
            product.productId = id
            insert(product)
        }
        return product
    }

/*
    val allProducts: LiveData<List<Product>> = productDao.getAllProducts()
    val searchResults = MutableLiveData<List<Product>>()

    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    fun insertProduct(newProduct: Product) {
        coroutineScope.launch(Dispatchers.IO) {
            productDao.insertProduct(newProduct)
        }
    }

    fun insertProduct(id: Long) {
        coroutineScope.launch(Dispatchers.IO) {
            if (asyncFind(id)?.isEmpty() == true) {
                val result = getProductData(id)
                if (result != null) {
                    productDao.insertProduct(result)
                }
            }
        }
    }

    fun deleteProduct(name: String) {
        coroutineScope.launch(Dispatchers.IO) {
            productDao.deleteProduct(name)
        }
    }

    fun findProduct(id: Long) {
        coroutineScope.launch(Dispatchers.Main) {
            searchResults.value = asyncFind(id)
        }
    }

    fun findProduct(name: String) {
        coroutineScope.launch(Dispatchers.Main) {
            searchResults.value = asyncFind(name)
        }
    }

    private suspend fun asyncFind(id: Long): List<Product>? =
        coroutineScope.async(Dispatchers.IO) {
            return@async productDao.findProduct(id)
        }.await()

    private suspend fun asyncFind(name: String): List<Product>? =
        coroutineScope.async(Dispatchers.IO) {
            return@async productDao.findProduct(name)
        }.await()

 */
}