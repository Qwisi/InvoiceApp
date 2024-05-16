package com.example.invoiceapp.model.entities.invoice

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
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
    @Delete
    suspend fun delete(invoice: Invoice): Int

    //----------------Query
    @Query("SELECT * FROM Invoice WHERE id = :id")
    fun getById(id: Int): Invoice
    @Query("SELECT * FROM Invoice")
    fun getLiveData(): LiveData<List<Invoice>>

    @Query("SELECT COUNT(*) FROM Invoice")
    fun getSize(): Int

    @Query("SELECT AVG(totalPrice) FROM Invoice")
    fun getAveragePrice(): Double

    @Query("SELECT COUNT(*) FROM Invoice WHERE dateCreated >= :dayStart AND dateCreated < :dayEnd")
    fun getSizeByDates(dayStart: Long, dayEnd: Long): Int

    @Query("SELECT AVG(totalPrice) FROM Invoice WHERE dateCreated >= :startOfDay AND dateCreated < :endOfDay")
    fun getAveragePriceByDates(startOfDay: Long, endOfDay: Long): Double

    //----------------Transactions
    @Transaction
    @Query("SELECT * FROM Invoice")
    fun getInvoicesWithIC(): LiveData<List<InvoiceWithIC>>

}


