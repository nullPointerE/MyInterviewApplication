package com.example.myapplication.repository

import com.example.myapplication.data.ConversionResult
import com.example.myapplication.data.ConverterDao
import kotlinx.coroutines.flow.Flow

class ConvertRepositoryImpl(private val dao: ConverterDao) : ConvertRepository {
    override suspend fun insertResult(conversionResult: ConversionResult) {
        dao.insertResult(conversionResult)
    }

    override suspend fun deleteResult(conversionResult: ConversionResult) {
        dao.deleteResult(conversionResult)
    }

    override suspend fun deleteAll() {
        dao.deleteAll()
    }

    override fun getResults(): Flow<List<ConversionResult>> =
        dao.getResults()

}