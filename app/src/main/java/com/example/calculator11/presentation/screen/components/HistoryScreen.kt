package com.example.calculator11.presentation.screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.calculator11.data.local.model.History

@Composable
fun HistoryScreen(
    histories: List<History>,
    onHistoryClicked: (String) -> Unit,
    onHistoryClearClicked: () -> Unit,
) {
    LazyColumn(modifier = Modifier.fillMaxWidth()) {
        item {
            Button(
                onClick = onHistoryClearClicked,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp)
            ) {
                Text("Clear history")
            }
        }
        items(histories) { history ->
            HistoryBox(history = history, onHistoryClicked = {
                onHistoryClicked(it)
            })
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .padding(horizontal = 8.dp)
                    .background(color = MaterialTheme.colorScheme.surface)
            )
        }

    }

}

@Composable
fun HistoryBox(
    history: History,
    onHistoryClicked: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .padding(12.dp)
        ,
        horizontalAlignment = Alignment.End
    ) {

        val text1 = history.history.split("=")[0]
        val text2 = history.history.split("=")[1]


        Text(
            text = text1,
            color = MaterialTheme.colorScheme.primary,
            maxLines = 1,
            modifier = Modifier
                .height(32.dp)
                .clickable {
                    onHistoryClicked(text1)
                }
        )
        Row {
            Text(
                text = "= ",
                color = MaterialTheme.colorScheme.secondary,
            )
            Text(
                text = text2,
                color = MaterialTheme.colorScheme.secondary,
                modifier = Modifier
                    .clickable {
                        onHistoryClicked(text2)
                    }
            )
        }


    }
}