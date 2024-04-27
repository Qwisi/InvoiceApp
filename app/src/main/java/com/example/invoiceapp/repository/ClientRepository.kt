package com.example.invoiceapp.repository

import com.example.invoiceapp.model.client.*

class ClientRepository(
    private val clientDao: ClientDao
) {
    fun get() = clientDao.getLiveData()
    suspend fun insert(client: Client) {
        clientDao.insert(client)
    }
    suspend fun update(client: Client) {
        clientDao.update(client)
    }

    suspend fun delete(client: Client) {
        clientDao.delete(client)
    }
}