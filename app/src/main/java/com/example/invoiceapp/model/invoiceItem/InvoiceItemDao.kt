package com.example.invoiceapp.model.invoiceItem

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction

@Dao
interface InvoiceItemDao {

    //----------------Insert
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(invoiceItem: InvoiceItem): Long

    //----------------Update

    //----------------Delete

    //----------------Query

    @Query("SELECT * FROM InvoiceItem")
    fun getLiveData(): LiveData<List<InvoiceItem>>

    @Query("SELECT * FROM InvoiceItem WHERE id = :id")
    fun getById(id: Int): InvoiceItem?
    //----------------Transactions
    @Transaction
    @Query("SELECT * FROM InvoiceItem")
    fun getInvoiceItemsWithProduct(): LiveData<List<InvoiceItemWithProduct>>
}