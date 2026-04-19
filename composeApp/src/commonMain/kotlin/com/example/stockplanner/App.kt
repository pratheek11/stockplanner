package com.example.stockplanner

import androidx.compose.runtime.*
import com.example.stockplanner.components.Home
import com.example.stockplanner.components.Login
import com.example.stockplanner.utils.models.AppState
import com.example.stockplanner.utils.theme.AppTheme

@Composable
fun App(appState: AppState) {
    val isLoggedIn = appState.isLoggedIn.collectAsState();
    AppTheme {
        if (!isLoggedIn.value) {
            Login(
                onLogin = { u, p -> appState.login(u, p) },
                onSignup = { u, p -> appState.signup(u, p) },
            );
        } else {
            Home()
        }
    }
}