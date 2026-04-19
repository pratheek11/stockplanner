package com.example.stockplanner.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.FontScaling
import androidx.compose.ui.unit.sp
import com.example.stockplanner.utils.theme.AppThemeValues
import com.example.stockplanner.utils.uiComponents.Buttons

@Composable
fun MenuBar(

) {
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .padding(AppThemeValues.spacing.small),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(
            text = "Stock Planner",
            color = AppThemeValues.colors.textDark,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
        )
        Buttons(
            text = "Profile",
            onClick = {  },
            severity = "secondary",
        )
    }
}