package com.example.stockplanner.utils.uiComponents

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.stockplanner.utils.theme.AppThemeValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width

@Composable
fun Buttons(
    text: String,
    onClick: () -> Unit,
    enabled: Boolean = true,
    severity: String,
    icon: @Composable (() -> Unit)? = null,
    modifier: Modifier = Modifier,
) {
    val btnColor = when (severity) {
        "warning" -> AppThemeValues.colors.warning
        "success" -> AppThemeValues.colors.success
        "error" -> AppThemeValues.colors.error
        "secondary" -> AppThemeValues.colors.secondary
        else -> AppThemeValues.colors.primary
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = modifier
            .clip(AppThemeValues.shapes.small)
            .background(if (enabled) btnColor else AppThemeValues.colors.disabled)
            .clickable(enabled = enabled) { onClick() }
            .padding(
                horizontal = AppThemeValues.spacing.medium,
                vertical = AppThemeValues.spacing.small
            )
    ) {

        // 👉 Icon
        if (icon != null) {
            icon()
            Spacer(modifier = Modifier.width(8.dp)) // spacing between icon & text
        }

        // 👉 Text
        Text(
            text = text,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
    }
}