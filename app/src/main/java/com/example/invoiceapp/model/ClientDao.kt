package com.example.invoiceapp.model


import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.invoiceapp.model.client.Client

@Dao
interface ClientDao {

    //----------------Insert

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(client: Client): Long

    @Transaction
    suspend fun insertWithValidation(client: Client): Int {
        validateCustomerData(client)
        return insert(client).toInt()
    }

    //----------------Update

    @Update
    fun update(client: Client): Int

    //----------------Delete

    @Delete
    fun delete(client: Client): Int

    //----------------Query

    @Query("SELECT * FROM Client")
    fun getData(): List<Client>

    @Query("SELECT * FROM Client")
    fun getLiveData(): LiveData<List<Client>>

    @Query("SELECT * FROM Client WHERE id = :id")
    fun getById(id: Int): Client?

    @Query("SELECT COUNT(*) FROM Client")
    fun getSize(): Int

    //----------------Utility

    // Validation logic for customer data
    private fun validateCustomerData(customer: Client) {
        if (!Regex("^[A-Za-z]+\$").matches(customer.name)) {
            throw IllegalArgumentException("Invalid name for client")
        }
        if (!Regex("^[A-Za-z]+\$").matches(customer.surname)) {
            throw IllegalArgumentException("Invalid surname for client")
        }
        if (!Regex("^[0-9]{10}\$").matches(customer.phoneNumber)) {
            throw IllegalArgumentException("Invalid phone number for client")
        }
        if (customer.discount < 0 || customer.discount > 1) {
            throw IllegalArgumentException("Invalid discount for client")
        }
        if (customer.totalAmount!! < 0.0) {
            throw IllegalArgumentException("Invalid total amount for client")
        }
    }
}