package com.example.myapplication.repository

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.myapplication.data.ConversionResult
import com.example.myapplication.data.ConverterDao

@Database(entities = [ConversionResult::class], version = 1)
abstract class ConverterDatabase : RoomDatabase() {
    abstract val converterDao: ConverterDao

    companion object {
        @Volatile
        private var INSTANCE: ConverterDatabase? = null
        fun getInstance(context: Context): ConverterDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        ConverterDatabase::class.java,
                        "converter_data_database"
                    ).build()
                }
                return instance
            }

        }
    }

}