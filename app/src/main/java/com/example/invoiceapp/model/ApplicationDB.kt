package com.example.invoiceapp.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.invoiceapp.model.client.*

@TypeConverters()
@Database (
    entities = [Client::class],
    version = 1
)
abstract class ApplicationDB: RoomDatabase() {

    abstract fun clientDao(): ClientDao

    companion object {
        @Volatile
        private var INSTANCE: ApplicationDB? = null

        fun getDatabase(context: Context): ApplicationDB {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = buildDatabase(context)
                }
            }
            return INSTANCE!!
        }

        private fun buildDatabase(context: Context): ApplicationDB {
            return Room.databaseBuilder(
                context.applicationContext,
                ApplicationDB::class.java,
                "application_database"
            ).build()
        }
    }
}