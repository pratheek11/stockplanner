package com.example.stockplanner

import androidx.compose.runtime.*
import com.example.stockplanner.components.Alerts
import com.example.stockplanner.components.Home
import com.example.stockplanner.components.Login
import com.example.stockplanner.utils.models.AppState
import com.example.stockplanner.utils.theme.AppTheme

@Composable
fun App(appState: AppState) {
    val isLoggedIn = appState.isLoggedIn.collectAsState();
    val message by appState.uiMessage.collectAsState()

    LaunchedEffect(Unit) {
        appState.loadQuote()
        appState.loadStockList()
    }

    AppTheme {
        if (message != null) {
            Alerts(
                message = message!!,
                onDismiss = { appState.clearMessage() }
            )
        }
        if (!isLoggedIn.value) {
            Login(
                onLogin = { u, p -> appState.login(u, p) },
                onSignup = { u, p, e -> appState.signup(u, p, e) },
            );
        } else {
            Home(appState)
        }
    }
}