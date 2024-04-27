package com.example.invoiceapp.model.category

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "Category"
)
data class Category(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val category: String
)