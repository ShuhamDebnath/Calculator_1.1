package com.example.calculator11.presentation.viewmodel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.calculator11.data.local.model.History
import com.example.calculator11.domain.repository.CalculatorRepository
import com.example.calculator11.presentation.event.UiEvent
import com.example.calculator11.presentation.state.UiState
import com.ezylang.evalex.Expression
import com.ezylang.evalex.data.EvaluationValue
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.math.BigDecimal
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class CalculatorViewModel @Inject constructor(
    private val repository: CalculatorRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(UiState())
    val uiState = _uiState.asStateFlow()


    init {
        // Load history initially
        viewModelScope.launch(Dispatchers.IO) {
            repository.getHistory().collect { historyList ->
                _uiState.update {
                    it.copy(historyList = historyList)
                }
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun onUiEvent(event: UiEvent) {
        when (event) {
            UiEvent.ClearLastValue -> {
                _uiState.update { currentState ->
                    currentState.copy(
                        mainExpression = removeLastCharacter(currentState.mainExpression)
                    )
                }
            }

            UiEvent.HistoryClicked -> {
//                Log.d("TAG", "onUiEvent: UiEvent.HistoryClicked")
                _uiState.update { currentState ->
                    currentState.copy(showHistory = !currentState.showHistory)
                }
            }

            is UiEvent.OnButtonClicked -> {
                _uiState.update { currentState ->
                    currentState.copy(
                        mainExpression = currentState.mainExpression + event.value
                    )
                }
            }

            UiEvent.IsEqualToClicked -> {

                val expression = _uiState.value.mainExpression
                val result = evaluateExpression(expression)

                _uiState.update { currentState ->
                    currentState.copy(
                        tempExpression = expression,
                        mainExpression = result.stringValue,
                    )
                }


                viewModelScope.launch(Dispatchers.IO) {
                    val history = History(
                        id = 0,
                        date = getCurrentDateTime(),
                        history = "${expression}\n=${result.stringValue}"
                    )
                    repository.createHistory(history)
                }

            }

            UiEvent.ClearScreen -> {
                _uiState.update {
                    it.copy(mainExpression = "", tempExpression = "")
                }
            }

            UiEvent.ChangeSign -> {

                _uiState.update { currentState ->

                    if (currentState.mainExpression.startsWith("-")) {
                        val updatedMainExpression = currentState.mainExpression.substring(1)
                        currentState.copy(
                            mainExpression = updatedMainExpression
                        )
                    } else {
                        val updatedMainExpression = "-" + currentState.mainExpression
                        currentState.copy(
                            mainExpression = updatedMainExpression
                        )
                    }


                }
            }

            UiEvent.OnTempExpressionClicked -> {
                _uiState.update {
                    it.copy(
                        mainExpression = it.tempExpression,
                        tempExpression = ""
                    )
                }
            }

            is UiEvent.OnEachHistoryClicked -> {
                _uiState.update {
                    it.copy(
                        mainExpression = event.history,
                        tempExpression = ""
                    )
                }
            }

            UiEvent.OnHistoryClearClicked -> {

//                Log.d("TAG", "onUiEvent: UiEvent.OnHistoryClearClicked ")
                viewModelScope.launch {
                    repository.deleteAllHistory()
                }.also {
                    _uiState.update {
                        it.copy(
                            historyList = emptyList()
                        )
                    }
                }


            }
        }

    }

    private fun evaluateExpression(expressionStr: String): EvaluationValue {
        return try {
            val expression = Expression(expressionStr)
            val result = expression.evaluate()

            var newResult = result.value.toString()
            val decimalIndex = newResult.indexOf(".")
            val lastIndex = newResult.lastIndex
            if (lastIndex - decimalIndex > 8) {
                newResult = newResult.substring(0, decimalIndex + 8)
            }
            EvaluationValue.stringValue(newResult)

        } catch (e: Exception) {

//            Log.e("TAG", "evaluateExpression: error ${e.message}")
            EvaluationValue.numberValue(BigDecimal.ZERO)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getCurrentDateTime(): String {
        val now = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        return now.format(formatter)
    }

    private fun removeLastCharacter(str: String): String {
        return if (str.isNotEmpty()) str.dropLast(1) else str
    }


}
