package com.example.invoiceapp.model.category

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface CategoryDao {

    //----------------Insert
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(category: Category): Long

    //----------------Update

    //----------------Delete

    //----------------Query
    @Query("SELECT * FROM Category")
    fun getLiveData(): LiveData<List<Category>>
}