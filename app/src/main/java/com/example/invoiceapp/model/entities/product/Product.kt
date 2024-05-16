package com.example.invoiceapp.model.entities.product

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.invoiceapp.model.entities.category.Category
import com.example.invoiceapp.model.entities.measurement.Measurement

@Entity(
    tableName = "Product",
    foreignKeys = [
        ForeignKey(
            entity = Category::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("idCategory"),
            onDelete = ForeignKey.RESTRICT
        ),
        ForeignKey(
            entity = Measurement::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("idMeasurement"),
            onDelete = ForeignKey.RESTRICT
        )
    ],
    indices = [
        Index(value = ["idCategory"]),
        Index(value = ["idMeasurement"])
    ]
)
data class Product(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val description: String?,
    var price: Double,
    var measurementValue: Double,
    var purchaseAmount: Int = 0,
    val idCategory: Int,
    val idMeasurement: Int
)