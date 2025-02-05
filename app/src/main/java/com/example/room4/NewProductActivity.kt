package com.example.room4

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.room4.entities.Product
import com.example.room4.viewModel.ProductViewModel

class NewProductActivity : AppCompatActivity() {

    private lateinit var editProductName: EditText
    private lateinit var editProductPrice: EditText

    // Aqui você cria o ViewModel utilizando o ViewModelFactory
    private val productViewModel: ProductViewModel by viewModels {
        ProductViewModel.ProductViewModelFactory(application)
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_product)

        // Inicializando os EditText
        editProductName = findViewById(R.id.edit_product_name)
        editProductPrice = findViewById(R.id.edit_product_price)

        val buttonSave = findViewById<Button>(R.id.button_save_product)
        buttonSave.setOnClickListener {
            val productName = editProductName.text.toString()
            val productPrice = editProductPrice.text.toString()

            if (TextUtils.isEmpty(productName) || TextUtils.isEmpty(productPrice)) {
                Toast.makeText(this, "Product not saved because fields are empty.", Toast.LENGTH_SHORT).show()
            } else {
                val price = productPrice.toDoubleOrNull() ?: 0.0
                val product = Product(name = productName, price = price)
                productViewModel.insertProduct(product)
                finish()  // Fecha a atividade após salvar o produto
            }
        }
    }
}
