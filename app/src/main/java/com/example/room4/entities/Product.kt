package com.example.room4.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "product")
data class Product(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,  // Auto-incremento do ID
    val name: String,
    val price: Double,
    val description: String? = null // Adicionando uma descrição como exemplo
)
