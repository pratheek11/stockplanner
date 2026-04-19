package com.example.stockplanner.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.stockplanner.utils.services.InputFilter
import com.example.stockplanner.utils.theme.AppThemeValues
import com.example.stockplanner.utils.uiComponents.Buttons
import com.example.stockplanner.utils.uiComponents.InputBox
import com.example.stockplanner.utils.uiComponents.Tabs

@Composable
fun Login(
    onLogin: (String, String) -> Unit,
    onSignup: (String, String) -> Unit,
) {
    Column(
        modifier = Modifier
            .safeContentPadding()
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        var selectedTab by remember { mutableStateOf(0) }
        val inputFilter = InputFilter();
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
        if(username.isNotBlank() && password.isNotBlank() && !inputFilter.validateLogin(username, password).success) {
            Text(
                inputFilter.validateLogin(username, password).errorMessage,
                color = AppThemeValues.colors.error,
            )
        }
        if (selectedTab == 0) {
            Buttons(
                text = "Login",
                onClick = { onLogin(username, password) },
                severity = "info",
                enabled = (inputFilter.validateLogin(username, password).success)
            ) {
            }
        } else {
            Buttons(
                text = "Signup",
                onClick = { onSignup(username, password) },
                severity = "info",
                enabled = (inputFilter.validateLogin(username, password).success)
            ) {
            }
        }
    }
}