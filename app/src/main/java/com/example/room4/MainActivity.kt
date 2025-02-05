package com.example.room4

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.room4.entities.Product
import com.example.room4.viewModel.ProductViewModel
import com.example.room4.adapter.ProductAdapter
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    // Instancia do ViewModel usando a fábrica
    private val productViewModel: ProductViewModel by viewModels {
        ProductViewModel.ProductViewModelFactory(application)
    }

    // Variáveis para RecyclerView e Adapter
    private lateinit var productAdapter: ProductAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inicializando o RecyclerView e o Adapter
        recyclerView = findViewById(R.id.recyclerview)
        productAdapter = ProductAdapter()
        recyclerView.adapter = productAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        
        productViewModel.allProducts.observe(this) { products ->
            products?.let { productAdapter.submitList(it) }
        }

// Botão para adicionar um novo produto
        val buttonAddProduct = findViewById<Button>(R.id.button_add_product)
        buttonAddProduct.setOnClickListener {
            // Iniciar a NewProductActivity
            val intent = Intent(this, NewProductActivity::class.java)
            startActivity(intent)
        }


        // Botão para filtrar produtos com preço acima de um valor
        val buttonFilterPrice = findViewById<Button>(R.id.button_filter_price)
        buttonFilterPrice.setOnClickListener {
            val minPrice = 50.0
            productViewModel.getProductsAbovePrice(minPrice).observe(this) { products ->
                products?.let {
                    if (it.isEmpty()) {
                        Toast.makeText(this, "Nenhum produto encontrado", Toast.LENGTH_SHORT).show()
                    }
                    productAdapter.submitList(it)
                }
            }
        }
    }
}
