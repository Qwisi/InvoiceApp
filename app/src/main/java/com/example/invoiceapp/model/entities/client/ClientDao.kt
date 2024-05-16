package com.example.invoiceapp.model.entities.client


import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface ClientDao {

    //----------------Insert
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(client: Client): Long

    //----------------Update
    @Update
    suspend fun update(client: Client): Int

    //----------------Delete
    @Delete
    suspend fun delete(client: Client): Int

    //----------------Query
    @Query("SELECT * FROM Client")
    fun getLiveData(): LiveData<List<Client>>

    @Query("SELECT * FROM Client")
    fun getSize(): Int

    @Query("SELECT AVG(totalAmount) FROM Client")
    fun getAverageTotalAmount(): Double
}