package com.example.invoiceapp.model.entities.product

import androidx.room.Embedded
import androidx.room.Relation
import com.example.invoiceapp.model.entities.category.Category
import com.example.invoiceapp.model.entities.measurement.Measurement

data class ProductWithCM(
    @Embedded val product: Product,
    @Relation(
        parentColumn = "idCategory",
        entityColumn = "id",
        entity = Category::class
    )
    val category: Category,

    @Relation(
        parentColumn = "idMeasurement",
        entityColumn = "id",
        entity = Measurement::class
    )
    val measurement: Measurement
)