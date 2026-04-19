package com.example.stockplanner.utils.uiComponents

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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

@Composable
fun Buttons(text: String, onClick: () -> Unit, enabled: Boolean = true, severity: String, icon: @Composable (() -> Unit)? = null) {
    var btnColor = AppThemeValues.colors.primary;
    if (severity == "info") {
        btnColor = AppThemeValues.colors.primary;
    } else if (severity == "warning") {
        btnColor = AppThemeValues.colors.warning;
    } else if (severity == "success") {
        btnColor = AppThemeValues.colors.success;
    } else if (severity == "error") {
        btnColor = AppThemeValues.colors.error;
    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clip(AppThemeValues.shapes.small)
            .clickable(enabled = enabled) { onClick() }
            .background(btnColor)
        ,
    ) {
        Text(
            text = text,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(AppThemeValues.spacing.medium, AppThemeValues.spacing.small)
        )
    }
}