package com.example.stockplanner.utils.uiComponents

import androidx.compose.foundation.border
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.stockplanner.utils.theme.AppThemeValues

@Composable
fun InputBox(
    label: String,
    value: String,
    onTextChange: (String) -> Unit
) {
    var isFocused by remember { mutableStateOf(false) }

    TextField(
        value = value,
        onValueChange = onTextChange,
        label = { Text(label) },

        modifier = Modifier
            .onFocusChanged { isFocused = it.isFocused }
            .clip(AppThemeValues.shapes.small) // circular shape
            .border(
                width = if (isFocused) 2.dp else 1.dp,
                color = if (isFocused) Color(0x801E88E5) else Color.Gray.copy(alpha = 0.3f),
                shape = AppThemeValues.shapes.small
            ),

        colors = TextFieldDefaults.colors(
            // background
            focusedContainerColor = AppThemeValues.colors.focusedContainerColor,
            unfocusedContainerColor = AppThemeValues.colors.unfocusedContainerColor,
            disabledContainerColor = AppThemeValues.colors.disabledContainerColor,

            // remove default underline
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        )
    )
}