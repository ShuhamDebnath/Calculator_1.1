package com.example.calculator11.presentation.event

sealed interface UiEvent {
    data class OnButtonClicked(val value :String): UiEvent
    data object HistoryClicked: UiEvent
    data object ClearLastValue: UiEvent
    data object ClearScreen: UiEvent
    data object IsEqualToClicked: UiEvent
    data object ChangeSign: UiEvent
    data object OnTempExpressionClicked: UiEvent
    data object OnHistoryClearClicked: UiEvent
    data class OnEachHistoryClicked(val history: String): UiEvent
}