package com.example.stockplanner.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import com.example.stockplanner.utils.models.AppState
import com.example.stockplanner.utils.theme.AppThemeValues
import com.example.stockplanner.utils.uiComponents.Buttons

@Composable
fun Profile(
    appState: AppState
) {
    val quote by appState.quote.collectAsState()

    Column (
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(AppThemeValues.spacing.small)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(AppThemeValues.spacing.small),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(AppThemeValues.spacing.large * 4)
                .clip(AppThemeValues.shapes.small)
                .background(Color.Gray.copy(alpha = 0.1f)),
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(AppThemeValues.spacing.small),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Funds"
                )
                Text(
                    text = "99,99,999"
                )
            }
        }
        quote?.let {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(AppThemeValues.shapes.small)
                    .background(Color.Gray.copy(alpha = 0.1f)),
                verticalArrangement = Arrangement.SpaceAround,
                horizontalAlignment = Alignment.End
            ) {
                Text(
                    text = quote?.quote.toString(),
                    modifier = Modifier.padding(AppThemeValues.spacing.small)
                )
                Text(
                    text = "~ " + quote?.author.toString(),
                    modifier = Modifier.padding(AppThemeValues.spacing.small),
                )
            }
        }
        Buttons(
            text = "Logout",
            onClick = { appState.logout() },
            severity = "error",
            modifier = Modifier.fillMaxWidth()
        )
    }
}