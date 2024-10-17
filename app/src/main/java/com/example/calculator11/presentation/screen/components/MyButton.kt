package com.example.calculator11.presentation.screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MyButton(modifier: Modifier = Modifier, value: String, onClick: () -> Unit) {
    Box(
        modifier = modifier
            .size(56.dp)
            .clip(CircleShape)
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = Modifier.background(color = Color.Transparent),
            text = value,
            color = MaterialTheme.colorScheme.onPrimary,
            fontSize = 32.sp
        )
    }
}

@Preview
@Composable
private fun MyButtonPrev() {
    MyButton(
        modifier = Modifier, value = "1", onClick = {}
    )
}