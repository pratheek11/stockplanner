package com.example.stockplanner.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.stockplanner.utils.models.AppState
import com.example.stockplanner.utils.theme.AppThemeValues

@Composable
fun NavigationBar(
    appState: AppState,
) {
    val currentScreen by appState.currentScreen.collectAsState()

    Column {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(Color.Gray.copy(alpha = 0.3f))
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .padding(AppThemeValues.spacing.small),
            horizontalArrangement = Arrangement.spacedBy(
                AppThemeValues.spacing.small,
                Alignment.CenterHorizontally
            ),
            verticalAlignment = Alignment.CenterVertically,
        ) {

            for (nav in appState.appNavigationButtons) {
                val isSelected = currentScreen == nav.screen
                Column(
                    modifier = Modifier
                        .clip(AppThemeValues.shapes.small)
                        .clickable {
                            appState.setCurrentScreen(nav.screen)
                        }
                        .padding(AppThemeValues.spacing.small)
                ) {
                    Text(
                        text = nav.label,
                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                        color = if (isSelected) AppThemeValues.colors.primary else Color.Black
                    )
                }
            }
        }
    }
}