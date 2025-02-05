package com.example.room4.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.room4.entities.Product
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {

    // Insere um produto na tabela
    @Insert
    suspend fun insertProduct(product: Product)

    // Retorna todos os produtos
    @Query("SELECT * FROM product")
    fun getAllProducts(): Flow<List<Product>>

    // Retorna todos os produtos com preço acima do valor mínimo fornecido
    @Query("SELECT * FROM product WHERE price > :minPrice")
    fun getProductsAbovePrice(minPrice: Double): Flow<List<Product>>
}
