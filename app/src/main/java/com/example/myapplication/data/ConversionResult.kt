package com.example.myapplication.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "result_table")
data class ConversionResult(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "result_id")
    val id: Int,
    @ColumnInfo(name = "result_message")
    val message: String,
)