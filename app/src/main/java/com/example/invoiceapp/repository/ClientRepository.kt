package com.example.invoiceapp.repository

import com.example.invoiceapp.model.ClientDao
import com.example.invoiceapp.model.client.Client


class ClientRepository(private val customerDao: ClientDao) {
    fun get() = customerDao.getLiveData()

    suspend fun insert(customer: Client) {
        customerDao.insert(customer)
    }

    suspend fun remove(customer: Client){
        customerDao.delete(customer)
    }

    suspend fun getById(id: Int): Client? {
        return customerDao.getById(id)
    }
}