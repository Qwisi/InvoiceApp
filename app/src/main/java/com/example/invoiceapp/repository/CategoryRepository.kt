package com.example.invoiceapp.repository

import com.example.invoiceapp.model.category.*

class CategoryRepository(
    private val categoryDao: CategoryDao
) {
    fun get() = categoryDao.getLiveData()

    suspend fun insert(category: Category) {
        categoryDao.insert(category)
    }
}