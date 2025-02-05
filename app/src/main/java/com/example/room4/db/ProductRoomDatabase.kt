package com.example.room4.db

import android.app.Application
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import androidx.room.migration.Migration
import com.example.room4.dao.ProductDao
import com.example.room4.entities.Product

@Database(entities = [Product::class], version = 2, exportSchema = false)
abstract class ProductRoomDatabase : RoomDatabase() {

    abstract fun productDao(): ProductDao

    companion object {
        @Volatile
        private var INSTANCE: ProductRoomDatabase? = null

        // Migração do banco de dados da versão 1 para a versão 2
        val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                // Exemplo de migração, pode adicionar novos campos ou outras alterações necessárias
                database.execSQL("ALTER TABLE product ADD COLUMN description TEXT")
            }
        }

        // Função que retorna a instância do banco de dados
        fun getDatabase(context: Context): ProductRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,  // Usando applicationContext
                    ProductRoomDatabase::class.java,
                    "product_database"
                )
                    .addMigrations(MIGRATION_1_2) // Adicionando a migração
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
