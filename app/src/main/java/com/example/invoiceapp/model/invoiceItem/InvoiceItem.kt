package com.example.invoiceapp.model.invoiceItem

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.invoiceapp.model.product.Product

@Entity(
    tableName = "InvoiceItem",
    foreignKeys = [
        ForeignKey(
            entity = Product::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("idProduct"),
            onDelete = ForeignKey.RESTRICT
        )
    ],
    indices = [
        Index(value = ["idProduct"])
    ]
)
data class InvoiceItem(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val quantity: Int,
    val price: Double,
    val idProduct: Int
)