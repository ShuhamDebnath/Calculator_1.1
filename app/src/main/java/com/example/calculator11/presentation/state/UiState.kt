package com.example.calculator11.presentation.state

import com.example.calculator11.data.local.model.History

data class UiState(
    val mainExpression: String = "",
    val tempExpression: String = "",
    val historyList: List<History> = emptyList(),
    val showHistory: Boolean = false
)