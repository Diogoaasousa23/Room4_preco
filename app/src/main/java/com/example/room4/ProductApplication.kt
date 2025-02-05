package com.example.room4

import android.app.Application
import com.example.room4.db.ProductRepository
import com.example.room4.db.ProductRoomDatabase

class ProductApplication : Application() {

    // Cria uma instância do repositório ProductRepository
    val repository: ProductRepository by lazy {
        ProductRepository(ProductRoomDatabase.getDatabase(this).productDao())
    }
}
