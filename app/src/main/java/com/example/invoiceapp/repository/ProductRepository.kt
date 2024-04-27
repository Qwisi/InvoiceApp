package com.example.invoiceapp.repository

import androidx.lifecycle.LiveData
import com.example.invoiceapp.model.category.Category
import com.example.invoiceapp.model.category.CategoryDao
import com.example.invoiceapp.model.measurement.Measurement
import com.example.invoiceapp.model.measurement.MeasurementDao
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

    // ------- Measurement
    fun getMeasurements() = measurementDao.getLiveData()
    suspend fun insert(measurement: Measurement) {
        measurementDao.insert(measurement)
    }
}