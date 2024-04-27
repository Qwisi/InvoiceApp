package com.example.invoiceapp.model.measurement

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
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(measurement: Measurement): Long

    //----------------Update

    //----------------Delete

    //----------------Query
    @Query("SELECT * FROM Measurement")
    fun getLiveData(): LiveData<List<Measurement>>

}