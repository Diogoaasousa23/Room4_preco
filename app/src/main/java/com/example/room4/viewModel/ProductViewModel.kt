package com.example.room4.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.room4.db.ProductRepository
import com.example.room4.db.ProductRoomDatabase
import com.example.room4.entities.Product
import kotlinx.coroutines.launch

class ProductViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: ProductRepository = ProductRepository(ProductRoomDatabase.getDatabase(application).productDao())

    val allProducts: LiveData<List<Product>> = repository.getAllProducts().asLiveData()

    fun getProductsAbovePrice(minPrice: Double): LiveData<List<Product>> {
        return repository.getProductsAbovePrice(minPrice).asLiveData()
    }

    fun insertProduct(product: Product) {
        viewModelScope.launch {
            repository.insertProduct(product)
        }
    }

    class ProductViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ProductViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return ProductViewModel(application) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}
