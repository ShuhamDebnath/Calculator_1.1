package com.example.calculator11.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.calculator11.data.local.model.History


@Database(entities = [(History::class)], version = 1, exportSchema = false)
abstract class CalculatorDatabase : RoomDatabase() {
    abstract fun historyDao(): HistoryDao
}