package com.example.invoiceapp.model.invoice

import androidx.room.Entity

@Entity(primaryKeys = ["invoiceId", "invoiceItemId"])
data class InvoiceCrossItems(
    val invoiceId: Int,
    val invoiceItemId: Int
)