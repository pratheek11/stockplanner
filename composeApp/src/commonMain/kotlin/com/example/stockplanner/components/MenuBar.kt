package com.example.stockplanner.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.stockplanner.utils.models.Screen
import com.example.stockplanner.utils.theme.AppThemeValues

@Composable
fun MenuBar(
    onProfileClick: (s: Screen) -> Unit,
) {
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .padding(AppThemeValues.spacing.small)
            .height(56.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Stock Planner",
            color = AppThemeValues.colors.textDark,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
        )
//        Buttons(
//            text = "Profile",
//            onClick = { onProfileClick() },
//            severity = "secondary",
//        )
    }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(1.dp)
            .background(Color.Gray.copy(alpha = 0.3f))
    )
}