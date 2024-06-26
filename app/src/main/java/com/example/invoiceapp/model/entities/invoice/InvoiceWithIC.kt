package com.example.invoiceapp.model.entities.invoice

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.example.invoiceapp.model.entities.client.Client
import com.example.invoiceapp.model.entities.invoiceItem.InvoiceItem

data class InvoiceWithIC(
    @Embedded val invoice: Invoice,
    @Relation(
        parentColumn = "idClient",
        entityColumn = "id",
        entity = Client::class
    )
    val client: Client,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            value = InvoiceCrossItems::class,
            parentColumn = "invoiceId",
            entityColumn = "invoiceItemId"
        )
    )
    val items: List<InvoiceItem>
)
