package com.example.indproproject.localDatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.indproproject.models.Row

/**
 *This class is used for room database.Here we will create all data tables for application.
 */
@Database(entities = [Row::class], version = 1, exportSchema = false)
abstract class AppLocalDatabase : RoomDatabase() {

    abstract fun rowDAO(): RowDAO

    companion object {
        fun getInstance(context: Context): AppLocalDatabase {
            return synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    AppLocalDatabase::class.java,
                    "Facts.db"
                ).build()
            }
        }
    }

    override fun clearAllTables() {

    }
}