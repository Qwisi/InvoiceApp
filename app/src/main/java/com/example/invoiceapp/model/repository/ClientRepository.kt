package com.example.invoiceapp.model.repository

import com.example.invoiceapp.model.entities.client.Client
import com.example.invoiceapp.model.entities.client.ClientDao

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
    fun getSize() = clientDao.getSize()
    fun getAverageTotalAmount() = clientDao.getAverageTotalAmount()
}