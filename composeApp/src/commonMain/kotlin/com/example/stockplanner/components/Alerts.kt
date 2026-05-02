package com.example.stockplanner.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.stockplanner.utils.theme.AppThemeValues
import com.example.stockplanner.utils.uiComponents.Buttons

@Composable
fun Alerts(
    message: String,
    onDismiss: () -> Unit
) {
    AlertDialog(
        modifier = Modifier
            .clip(AppThemeValues.shapes.small)
            .background(Color.White),
        onDismissRequest = onDismiss,
        confirmButton = {
            Buttons(
                text = "Close",
                severity = "info",
                onClick = onDismiss,
                modifier = Modifier
            )
        },
        text = {
            Text(message)
        }
    )
}