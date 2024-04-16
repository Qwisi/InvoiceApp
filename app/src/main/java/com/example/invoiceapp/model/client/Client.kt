package com.example.invoiceapp.model.client

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "Client",
    indices = [Index(value = ["phoneNumber"], unique = true)]
)
data class Client(
    @PrimaryKey (autoGenerate = true)
    val id: Int = 0,
    var name: String,
    var surname: String,
    var phoneNumber: String,
    var discount: Double,
    var totalAmount: Double,
    var specialNotes: String?
)