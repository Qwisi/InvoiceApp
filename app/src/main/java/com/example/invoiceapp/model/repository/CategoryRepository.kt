package com.example.invoiceapp.model.repository

import com.example.invoiceapp.model.category.*
import com.example.invoiceapp.model.entities.category.Category
import com.example.invoiceapp.model.entities.category.CategoryDao

class CategoryRepository(
    private val categoryDao: CategoryDao
) {
    fun get() = categoryDao.getLiveData()

    suspend fun insert(category: Category) {
        categoryDao.insert(category)
    }
}