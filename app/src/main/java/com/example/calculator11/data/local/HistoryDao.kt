package com.example.calculator11.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.calculator11.data.local.model.History
import kotlinx.coroutines.flow.Flow


@Dao
interface HistoryDao {
    @Upsert
    suspend fun upsertHistory(history: History)

    @Delete
    suspend fun deleteHistory(history: History)

//    @Query("SELECT * FROM calculator_history")
//    fun getAllHistory(): Flow<List<History>>

    @Query("SELECT * FROM calculator_history ORDER BY id DESC")
    fun getAllHistory(): Flow<List<History>>

    @Query("DELETE FROM calculator_history")
    suspend fun deleteAllHistory()

}
