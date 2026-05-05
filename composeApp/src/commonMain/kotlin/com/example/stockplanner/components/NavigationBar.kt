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
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.unit.sp
import com.example.stockplanner.utils.models.AppState
import com.example.stockplanner.utils.theme.AppTheme
import com.example.stockplanner.utils.theme.AppThemeValues

@Composable
fun NavigationBar(
    appState: AppState,
) {
    val currentScreen by appState.currentScreen.collectAsState()

    Column (
        modifier = Modifier
            .clip(AppThemeValues.shapes.small)
    ){
        // Top divider
//        Box(
//            modifier = Modifier
//                .fillMaxWidth()
//                .height(1.dp)
//                .background(Color.Gray.copy(alpha = 0.15f))
//        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
                .background(Color.White)
                .clip(AppThemeValues.shapes.small),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            appState.appNavigationButtons.forEach { nav ->
                val isSelected = currentScreen == nav.screen

                Column(
                    modifier = Modifier
                        .clip(AppThemeValues.shapes.small)
                        .clickable { appState.setCurrentScreen(nav.screen) }
                        .padding(horizontal = 20.dp, vertical = 8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    // Icon placeholder — replace with actual icons
                    Text(
                        text = when (nav.label) {
                            "Charts" -> "📈"
                            "Holdings" -> "💼"
                            "Profile" -> "👤"
                            else -> "•"
                        },
                        fontSize = 18.sp
                    )
                    Text(
                        text = nav.label,
                        fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Normal,
                        color = if (isSelected) AppThemeValues.colors.primary else Color.Gray,
                        fontSize = 11.sp
                    )
                    // Active indicator dot
                    Box(
                        modifier = Modifier
                            .height(3.dp)
                            .width(if (isSelected) 16.dp else 0.dp)
                            .clip(androidx.compose.foundation.shape.RoundedCornerShape(2.dp))
                            .background(
                                if (isSelected) AppThemeValues.colors.primary
                                else Color.Transparent
                            )
                    )
                }
            }
        }
    }
}