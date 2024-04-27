package com.example.invoiceapp.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.invoiceapp.model.measurement.Measurement
import com.example.invoiceapp.model.product.Product
import com.example.invoiceapp.model.product.ProductWithCM
import com.example.invoiceapp.repository.ProductRepository
import kotlinx.coroutines.launch

class ProductViewModel(private val repository: ProductRepository) : ViewModel() {

    val products = repository.getProducts()

    val liveData = repository.getLiveData()
    fun insert(product: Product) = viewModelScope.launch {
        repository.insert(product)
    }
    fun update(product: Product) = viewModelScope.launch {
        repository.update(product)
    }

    fun delete(product: Product) = viewModelScope.launch {
        repository.delete(product)
    }

    fun delete(productWithCM: ProductWithCM) = viewModelScope.launch {
        repository.delete(repository.getById(productWithCM.product.id))
    }

    // ------------ Measurements
    val measurements = repository.getMeasurements()
    fun insert(measurement: Measurement) = viewModelScope.launch {
        repository.insert(measurement)
    }
}