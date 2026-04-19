package com.example.stockplanner.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.stockplanner.utils.theme.AppThemeValues
import com.example.stockplanner.utils.uiComponents.Buttons
import com.example.stockplanner.utils.uiComponents.InputBox
import com.example.stockplanner.utils.uiComponents.Tabs

@Composable
fun Login(
    onLogin: () -> Unit,
) {
    var showContent by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .safeContentPadding()
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        var selectedTab by remember { mutableStateOf(0) }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(
                AppThemeValues.spacing.medium,
                alignment = Alignment.CenterHorizontally
            ),
        ) {
            Tabs(
                isSelected = selectedTab == 0,
                onClick = { selectedTab = 0 },
                text = "Login"
            )
            Tabs(
                isSelected = selectedTab == 1,
                onClick = { selectedTab = 1 },
                text = "Signup"
            )
        }
        Spacer(Modifier.height(20.dp))
        var username by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }
        if (selectedTab == 0) {

            InputBox(
                value = username,
                onTextChange = { username = it },
                label = "Username"
            )
            Spacer(Modifier.height(20.dp))
            InputBox(
                value = password,
                onTextChange = { password = it },
                label = "Password"
            )
            Spacer(Modifier.height(20.dp))
            Buttons(
                text = "Login",
                onClick = { onLogin() },
                severity = "info",
            ) {
            }
        } else {

            InputBox(
                value = username,
                onTextChange = { username = it },
                label = "Username"
            )
            Spacer(Modifier.height(20.dp))
            InputBox(
                value = password,
                onTextChange = { password = it },
                label = "Password"
            )
            Spacer(Modifier.height(20.dp))
            Buttons(
                text = "Signup",
                onClick = { showContent = false },
                severity = "info",
            ) {
            }
        }
    }
}