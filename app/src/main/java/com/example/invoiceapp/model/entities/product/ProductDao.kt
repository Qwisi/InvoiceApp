package com.example.invoiceapp.model.entities.product

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update

@Dao
interface ProductDao {

    //----------------Insert
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(product: Product): Long

    //----------------Update
    @Update
    suspend fun update(product: Product): Int

    //----------------Delete

    @Delete
    suspend fun delete(product: Product): Int

    //----------------Query
    @Query("SELECT * FROM Product")
    fun getLiveData(): LiveData<List<Product>>
    @Query("SELECT * FROM Product WHERE id = :id")
    fun getById(id: Int): Product

    @Query("SELECT * FROM Product")
    fun getSize(): Int

    @Query("SELECT AVG(price) FROM Product")
    fun getAveragePrice(): Double

    //----------------Transactions
    @Transaction
    @Query("SELECT * FROM Product")
    fun getProductsAndCM(): LiveData<List<ProductWithCM>>
}