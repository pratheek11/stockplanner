package com.example.stockplanner.utils.uiComponents

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import com.example.stockplanner.utils.theme.AppThemeValues

@Composable
fun Tabs(text: String, isSelected: Boolean, onClick: () -> Unit, enabled: Boolean = true) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clip(AppThemeValues.shapes.small)
            .clickable(enabled = enabled) { onClick() }
            .background(if (isSelected) AppThemeValues.colors.tabSelected else AppThemeValues.colors.tabUnSelected)
            ,
    ) {
        Text(
            text = text,
            fontWeight = FontWeight.Bold,
            color = AppThemeValues.colors.textDark,
            modifier = Modifier
                .padding(AppThemeValues.spacing.large, AppThemeValues.spacing.small)
        )
    }
}