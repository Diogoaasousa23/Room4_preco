package com.example.room4.db

import com.example.room4.dao.ProductDao
import com.example.room4.entities.Product
import kotlinx.coroutines.flow.Flow

class ProductRepository(private val productDao: ProductDao) {

    // Função para obter todos os produtos
    fun getAllProducts(): Flow<List<Product>> {
        return productDao.getAllProducts()  // Retorna Flow
    }

    // Função para obter produtos com preço maior que um valor específico
    fun getProductsAbovePrice(minPrice: Double): Flow<List<Product>> {
        return productDao.getProductsAbovePrice(minPrice)  // Retorna Flow
    }

    // Função para inserir um novo produto
    suspend fun insertProduct(product: Product) {
        productDao.insertProduct(product)
    }
}
