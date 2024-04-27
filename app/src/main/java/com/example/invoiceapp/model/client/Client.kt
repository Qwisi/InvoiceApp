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
    var country: String,
    var city: String,
    var street: String,
    var houseNumber: Int,
    var postCode: String,
    var discount: Double,
    var totalAmount: Double = 0.0,
    var specialNotes: String?
)