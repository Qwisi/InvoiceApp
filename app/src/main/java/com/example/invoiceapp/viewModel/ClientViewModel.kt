package com.example.invoiceapp.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.invoiceapp.model.client.Client
import com.example.invoiceapp.repository.ClientRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ClientViewModel(private val repository: ClientRepository) : ViewModel() {
    val clients = repository.get()

    fun insertClient(client: Client) = viewModelScope.launch {
        repository.insert(client)
    }

    fun removeClients(client: Client) = viewModelScope.launch {
        repository.remove(client)
    }

    private val _client = MutableStateFlow<Client?>(null)
    val client: StateFlow<Client?> = _client

    fun fetchClientById(id: Int) {
        viewModelScope.launch {
            _client.value = repository.getById(id)
        }
    }
}