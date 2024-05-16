package com.example.invoiceapp.model.entities.invoice

import androidx.room.Entity

@Entity(primaryKeys = ["invoiceId", "invoiceItemId"])
data class InvoiceCrossItems(
    val invoiceId: Int,
    val invoiceItemId: Int
)