package com.example.invoiceapp.model.product

import androidx.room.Embedded
import androidx.room.Relation
import com.example.invoiceapp.model.category.Category
import com.example.invoiceapp.model.measurement.Measurement

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