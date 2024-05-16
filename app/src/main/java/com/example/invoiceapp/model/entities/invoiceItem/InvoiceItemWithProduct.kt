package com.example.invoiceapp.model.entities.invoiceItem

import androidx.room.Embedded
import androidx.room.Relation
import com.example.invoiceapp.model.entities.product.Product

data class InvoiceItemWithProduct(
    @Embedded val invoiceItem: InvoiceItem,
    @Relation(
        parentColumn = "idProduct",
        entityColumn = "id",
        entity = Product::class
    )
    val product: Product
)