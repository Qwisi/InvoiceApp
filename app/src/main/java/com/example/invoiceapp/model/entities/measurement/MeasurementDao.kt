package com.example.invoiceapp.model.entities.measurement

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface MeasurementDao {

    //----------------Insert
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(measurement: Measurement): Long

    //----------------Update
    @Update
    suspend fun update(measurement: Measurement)

    //----------------Delete
    @Delete
    suspend fun delete(measurement: Measurement)

    //----------------Query
    @Query("SELECT * FROM Measurement")
    fun getLiveData(): LiveData<List<Measurement>>

}