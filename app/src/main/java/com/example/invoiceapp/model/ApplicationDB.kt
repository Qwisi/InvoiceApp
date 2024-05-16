package com.example.invoiceapp.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.invoiceapp.model.entities.category.Category
import com.example.invoiceapp.model.entities.category.CategoryDao
import com.example.invoiceapp.model.entities.client.Client
import com.example.invoiceapp.model.entities.client.ClientDao
import com.example.invoiceapp.model.converters.DateConverter
import com.example.invoiceapp.model.entities.invoice.Invoice
import com.example.invoiceapp.model.entities.invoice.InvoiceCrossItems
import com.example.invoiceapp.model.entities.invoice.InvoiceCrossItemsDao
import com.example.invoiceapp.model.entities.invoice.InvoiceDao
import com.example.invoiceapp.model.entities.invoiceItem.InvoiceItem
import com.example.invoiceapp.model.entities.invoiceItem.InvoiceItemDao
import com.example.invoiceapp.model.entities.measurement.Measurement
import com.example.invoiceapp.model.entities.measurement.MeasurementDao
import com.example.invoiceapp.model.entities.product.Product
import com.example.invoiceapp.model.entities.product.ProductDao


@TypeConverters(DateConverter::class)
@Database (
    entities = [Client::class, Category::class,
        Product::class, Measurement::class,
        InvoiceItem::class, Invoice::class,
        InvoiceCrossItems::class],
    version = 1
)
abstract class ApplicationDB: RoomDatabase() {

    abstract fun clientDao(): ClientDao
    abstract fun categoryDao(): CategoryDao
    abstract fun productDao(): ProductDao
    abstract fun measurementDao(): MeasurementDao
    abstract fun invoiceItemDao(): InvoiceItemDao
    abstract fun invoiceDao(): InvoiceDao
    abstract fun invoiceCrossItemsDao(): InvoiceCrossItemsDao

    companion object {
        @Volatile
        private var INSTANCE: ApplicationDB? = null

        fun getDatabase(context: Context): ApplicationDB {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = buildDatabase(context)
                }
            }
            return INSTANCE!!
        }

        private fun buildDatabase(context: Context): ApplicationDB {
            return Room.databaseBuilder(
                context.applicationContext,
                ApplicationDB::class.java,
                "application_database"
            ).build()
        }

        // Temp database for preview of screens. Could be removed after..
        fun getPreviewDatabase(context: Context): ApplicationDB {
            return Room.inMemoryDatabaseBuilder(
                context.applicationContext,
                ApplicationDB::class.java
            ).allowMainThreadQueries()
                .build()
        }
    }
}