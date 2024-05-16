package com.example.invoiceapp.model.repository

import androidx.lifecycle.LiveData
import com.example.invoiceapp.model.entities.category.Category
import com.example.invoiceapp.model.entities.category.CategoryDao
import com.example.invoiceapp.model.entities.measurement.Measurement
import com.example.invoiceapp.model.entities.measurement.MeasurementDao
import com.example.invoiceapp.model.entities.product.Product
import com.example.invoiceapp.model.entities.product.ProductDao
import com.example.invoiceapp.model.product.*

class ProductRepository(
    private val productDao: ProductDao,
    private val measurementDao: MeasurementDao
) {
    fun getProducts() = productDao.getProductsAndCM()
    fun getLiveData() = productDao.getLiveData()
    fun getById(id: Int) = productDao.getById(id)

    suspend fun insert(product: Product) {
        productDao.insert(product)
    }
    suspend fun update(product: Product) {
        productDao.update(product)
    }

    suspend fun delete(product: Product) {
        productDao.delete(product)
    }

    // ------- Queries

    fun getSize() = productDao.getSize()

    fun getAveragePrice() = productDao.getAveragePrice()

    // ------- Measurement
    fun getMeasurements() = measurementDao.getLiveData()
    suspend fun insert(measurement: Measurement) {
        measurementDao.insert(measurement)
    }
}