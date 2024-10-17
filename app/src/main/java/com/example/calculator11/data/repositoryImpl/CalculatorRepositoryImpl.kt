package com.example.calculator11.data.repositoryImpl

import com.example.calculator11.data.local.HistoryDao
import com.example.calculator11.data.local.model.History
import com.example.calculator11.domain.repository.CalculatorRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CalculatorRepositoryImpl @Inject constructor(private  val historyDao: HistoryDao): CalculatorRepository {
    override suspend fun getHistory(): Flow<List<History>> {
        return historyDao.getAllHistory()
    }

    override suspend fun createHistory(history: History) {
        return historyDao.upsertHistory(history)
    }

    override suspend fun deleteHistory(history: History) {
        return historyDao.deleteHistory(history)
    }
    override suspend fun deleteAllHistory() {
        return historyDao.deleteAllHistory()
    }
}