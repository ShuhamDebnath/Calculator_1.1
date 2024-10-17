package com.example.calculator11.domain.repository

import com.example.calculator11.data.local.model.History
import kotlinx.coroutines.flow.Flow

interface CalculatorRepository {
    suspend fun getHistory(): Flow<List<History>>

    suspend fun deleteAllHistory()

    suspend fun createHistory(history: History)

    suspend fun deleteHistory(history: History)
}