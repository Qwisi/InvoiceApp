package com.example.invoiceapp.model.entities.invoice

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.invoiceapp.model.entities.client.Client
import com.example.invoiceapp.model.entities.invoiceItem.InvoiceItem
import java.util.Date

@Entity(
    tableName = "Invoice",
    foreignKeys = [
        ForeignKey(
            entity = Client::class,
            parentColumns = ["id"],
            childColumns = ["idClient"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value = ["idClient"])]
)
data class Invoice(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val number: String,
    val discount: Double,
    val totalPrice: Double,
    val dateCreated: Date,
    val idClient: Int,
)