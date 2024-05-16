package com.example.invoiceapp.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.invoiceapp.model.entities.client.Client
import com.example.invoiceapp.model.repository.ClientRepository
import kotlinx.coroutines.launch

class ClientViewModel(
    private val repository: ClientRepository
) : ViewModel() {

    val clients = repository.get()

    fun insert(client: Client) = viewModelScope.launch {
        repository.insert(client)
    }

    fun update(client: Client) = viewModelScope.launch {
        repository.update(client)
    }

    fun delete(client: Client) = viewModelScope.launch {
        repository.delete(client)
    }

    fun getSize() = repository.getSize()

    fun getAverageTotalAmount() = repository.getAverageTotalAmount()
}