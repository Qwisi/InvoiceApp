package com.example.invoiceapp.model.measurement

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "Measurement"
)
data class Measurement(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val unit: String
)