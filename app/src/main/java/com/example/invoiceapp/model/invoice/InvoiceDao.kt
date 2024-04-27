package com.example.invoiceapp.model.invoice

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction

@Dao
interface InvoiceDao {

    //----------------Insert
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(invoice: Invoice): Long

    //----------------Update

    //----------------Delete

    //----------------Query
    @Query("SELECT * FROM Invoice")
    fun getLiveData(): LiveData<List<Invoice>>

    //----------------Transactions
    @Transaction
    @Query("SELECT * FROM Invoice")
    fun getInvoicesWithIC(): LiveData<List<InvoiceWithIC>>

}


