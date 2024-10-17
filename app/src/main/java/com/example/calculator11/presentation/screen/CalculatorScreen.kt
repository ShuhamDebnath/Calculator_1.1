package com.example.calculator11.presentation.screen


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Backspace
import androidx.compose.material.icons.filled.Calculate
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Scale
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calculator11.presentation.state.UiState
import com.example.calculator11.presentation.event.UiEvent
import com.example.calculator11.presentation.screen.components.HistoryScreen
import com.example.calculator11.presentation.screen.components.MyButton
import com.example.calculator11.ui.theme.Calculator11Theme

@Composable
fun CalculatorScreen(
    state: UiState,
    onEvent: (UiEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxSize()) {

        Box(
            modifier = Modifier
                .weight(.5f)
                .padding(12.dp)
        ) {
            ExpressionBox(modifier = Modifier, state, onEvent)
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = MaterialTheme.colorScheme.primaryContainer),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            MoreIcon(onEvent)
        }
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .size(1.dp)
                .background(color = MaterialTheme.colorScheme.secondary)
        )
        Box(
            modifier = Modifier
                .weight(.5f)
                .background(color = MaterialTheme.colorScheme.primaryContainer)
        ) {

            if (state.showHistory) {
                HistoryScreen(state.historyList, onHistoryClicked = {
                    onEvent(UiEvent.OnEachHistoryClicked(it))

                }, onHistoryClearClicked = {
                    onEvent(UiEvent.OnHistoryClearClicked)
                })
            } else {
                ButtonBox( onEvent)

            }


        }

    }
}


@Composable
fun ExpressionBox(
    modifier: Modifier = Modifier,
    state: UiState,
    onEvent: (UiEvent) -> Unit,
) {
    Column(modifier = modifier.fillMaxWidth(), horizontalAlignment = Alignment.End) {
        Spacer(Modifier.weight(2f))
        Text(
            text = state.mainExpression,
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(vertical = 12.dp)
        )
        Spacer(Modifier.weight(.5f))
        Text(
            text = state.tempExpression,
            fontSize = 24.sp,
            fontWeight = FontWeight.W300,
            color = MaterialTheme.colorScheme.inversePrimary,
            modifier = Modifier
                .padding(vertical = 8.dp)
                .clickable {
                    onEvent(UiEvent.OnTempExpressionClicked)
                }
        )
        Spacer(Modifier.weight(.5f))
    }
}


@Composable
fun MoreIcon(
    onEvent: (UiEvent) -> Unit,
) {
    IconButton(onClick = {
//        Log.d("TAG", "MoreIcon: clicked")
        onEvent(UiEvent.HistoryClicked)
    }) {
        Icon(imageVector = Icons.Default.History, contentDescription = null)
    }
    IconButton(onClick = {

    }) {
        Icon(imageVector = Icons.Default.Scale, contentDescription = null)
    }
    IconButton(onClick = {

    }) {
        Icon(imageVector = Icons.Default.Calculate, contentDescription = null)
    }
    IconButton(onClick = {
        onEvent(UiEvent.ClearLastValue)
    }) {
        Icon(
            imageVector = Icons.AutoMirrored.Filled.Backspace,
            contentDescription = null
        )
    }
}

@Composable
fun ButtonBox(
    onEvent: (UiEvent) -> Unit,
) {

    val primary = MaterialTheme.colorScheme.primary
//    val onPrimary = MaterialTheme.colorScheme.onPrimary
    val secondary = MaterialTheme.colorScheme.secondary
//    val onSecondary = MaterialTheme.colorScheme.onSecondary
    val tertiary = MaterialTheme.colorScheme.tertiary
//    val onTertiary = MaterialTheme.colorScheme.onTertiary
    Column {
        Spacer(Modifier.weight(2f))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            MyButton(
                value = "C",
                onClick = {
                    onEvent(UiEvent.ClearScreen)
                },
                modifier = Modifier.background(
                    color = MaterialTheme.colorScheme.error,
                    shape = RoundedCornerShape(24.dp)
                )
            )
            MyButton(
                value = "(",
                onClick = {
                    onEvent(UiEvent.OnButtonClicked("("))
                },
                modifier = Modifier.background(
                    color = secondary,
                    shape = RoundedCornerShape(24.dp)
                )
            )
            MyButton(
                value = ")",
                onClick = {
                    onEvent(UiEvent.OnButtonClicked(")"))
                },
                modifier = Modifier.background(
                    color =secondary,
                    shape = RoundedCornerShape(24.dp)
                )
            )
            MyButton(
                value = "/",
                onClick = {
                    onEvent(UiEvent.OnButtonClicked("/"))
                },
                modifier = Modifier.background(
                    color = secondary,
                    shape = RoundedCornerShape(24.dp)
                )
            )
        }
        Spacer(Modifier.weight(1f))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {

            for (i in 7..9) {
                MyButton(
                    value = i.toString(),
                    onClick = {
                        onEvent(UiEvent.OnButtonClicked(i.toString()))
                    },
                    modifier = Modifier.background(
                        color = primary,
                        shape = RoundedCornerShape(24.dp)
                    )
                )
            }
            MyButton(
                value = "*",
                onClick = {
                    onEvent(UiEvent.OnButtonClicked("*"))
                },
                modifier = Modifier.background(
                    color = secondary,
                    shape = RoundedCornerShape(24.dp)
                )
            )
        }
        Spacer(Modifier.weight(1f))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {

            for (i in 4..6) {
                MyButton(
                    value = i.toString(),
                    onClick = {
                        onEvent(UiEvent.OnButtonClicked(i.toString()))
                    },
                    modifier = Modifier.background(
                        color = primary,
                        shape = RoundedCornerShape(24.dp)
                    )
                )
            }
            MyButton(
                value = "-",
                onClick = {
                    onEvent(UiEvent.OnButtonClicked("-"))
                },
                modifier = Modifier.background(
                    color = secondary,
                    shape = RoundedCornerShape(24.dp)
                )
            )
        }
        Spacer(Modifier.weight(1f))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {

            for (i in 1..3) {
                MyButton(
                    value = i.toString(),
                    onClick = {
                        onEvent(UiEvent.OnButtonClicked(i.toString()))
                    },
                    modifier = Modifier.background(
                        color = primary,
                        shape = RoundedCornerShape(24.dp)
                    )
                )
            }
            MyButton(
                value = "+",
                onClick = {
                    onEvent(UiEvent.OnButtonClicked("+"))
                },
                modifier = Modifier.background(
                    color = secondary,
                    shape = RoundedCornerShape(24.dp)
                )
            )
        }
        Spacer(Modifier.weight(1f))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {


            MyButton(
                value = "+/-",
                onClick = {
                    onEvent(UiEvent.ChangeSign)
                },
                modifier = Modifier.background(
                    color = primary,
                    shape = RoundedCornerShape(24.dp)
                )
            )
            MyButton(
                value = "0",
                onClick = {
                    onEvent(UiEvent.OnButtonClicked("0"))
                },
                modifier = Modifier.background(
                    color = primary,
                    shape = RoundedCornerShape(24.dp)
                )
            )
            MyButton(
                value = ".",
                onClick = {
                    onEvent(UiEvent.OnButtonClicked("."))
                },
                modifier = Modifier.background(
                    color = primary,
                    shape = RoundedCornerShape(24.dp)
                )
            )
            MyButton(
                value = "=",
                onClick = {
                    onEvent(UiEvent.IsEqualToClicked)
                },
                modifier = Modifier.background(
                    color = tertiary,
                    shape = RoundedCornerShape(24.dp)
                )
            )
        }
        Spacer(Modifier.weight(2f))
    }


}


@Preview(showSystemUi = true)
@Composable
private fun CalculatorScreenPrev() {
    Calculator11Theme {

        CalculatorScreen(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp),
            state = UiState(mainExpression = "23+56-54", tempExpression = "560"),
            onEvent = {

            }
        )


    }

}