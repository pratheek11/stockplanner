package com.example.stockplanner

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import com.example.stockplanner.components.Login
import com.example.stockplanner.utils.models.AppState
import com.example.stockplanner.utils.theme.AppTheme

@Composable
fun App(appState: AppState) {
    val isLoggedIn = appState.isLoggedIn.collectAsState();
    AppTheme {
        if (!isLoggedIn.value) {
            Login(
                onLogin = { appState.login() }
            );
        } else {
            Text("Home!")
        }
    }
}