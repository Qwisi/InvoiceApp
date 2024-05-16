package com.example.invoiceapp.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import com.example.invoiceapp.model.entities.category.Category
import com.example.invoiceapp.model.repository.CategoryRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CategoryViewModel(
    private val repository: CategoryRepository
) : ViewModel() {

    val categories = repository.get()
    fun insertCategory(category: Category) = viewModelScope.launch {
        repository.insert(category)
    }
}