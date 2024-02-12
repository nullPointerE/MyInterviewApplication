package com.example.myapplication.repository

import com.example.myapplication.data.ConversionResult
import kotlinx.coroutines.flow.Flow

interface ConvertRepository {
    suspend fun insertResult(conversionResult: ConversionResult)
    suspend fun deleteResult(conversionResult: ConversionResult)
    suspend fun deleteAll()
    fun getResults(): Flow<List<ConversionResult>>
}