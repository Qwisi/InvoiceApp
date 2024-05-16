package com.example.invoiceapp.model.entities.invoice

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface InvoiceCrossItemsDao {

    //----------------Insert
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(invoiceCrossItems: InvoiceCrossItems): Long

    //----------------Update

    //----------------Delete

    //----------------Query
    @Query("SELECT * FROM InvoiceCrossItems")
    fun getLiveData(): LiveData<List<InvoiceCrossItems>>
}