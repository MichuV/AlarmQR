package com.example.alarmqr.ui.theme

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.fitInside
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun WideButton(buttonText: String, buttonOnClick: () -> Unit) {
    ElevatedButton(
        onClick = buttonOnClick,
        modifier = Modifier.fillMaxWidth().height(50.dp),
        shape = RoundedCornerShape(20.dp),
        colors = ButtonDefaults.elevatedButtonColors(
            containerColor = MaterialTheme.colorScheme.tertiary
        )
    ) {
        Text(text = buttonText, style = MaterialTheme.typography.titleMedium)
    }
}

@Composable
fun FitButton(buttonText: String, buttonOnClick: () -> Unit) {
    Button(
        onClick = buttonOnClick,
        modifier = Modifier.height(50.dp),
        shape = RoundedCornerShape(20.dp),
        colors = ButtonDefaults.buttonColors(
            contentColor = MaterialTheme.colorScheme.tertiary
        )
    ) {
        Text(text = buttonText, style = MaterialTheme.typography.titleMedium)
    }
}