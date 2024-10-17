package com.example.calculator11.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "calculator_history")
data class History(
    @PrimaryKey(autoGenerate = true)
    val id :Int,
    val date :String,
    val history: String
)
