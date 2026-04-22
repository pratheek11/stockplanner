package com.example.stockplanner.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.example.stockplanner.utils.models.AppState
import com.example.stockplanner.utils.models.Screen

@Composable
fun Home(

) {
    val appState = remember { AppState() }
    val currentScreen by appState.currentScreen.collectAsState()

    Column(
        modifier = Modifier.safeContentPadding()
    ) {
        MenuBar { s -> appState.setCurrentScreen(s) }
        Column(
            modifier = Modifier
            .fillMaxWidth()
            .weight(1f),
        ) {
            when (currentScreen) {
                Screen.Charts -> ChartsHome(appState)
                Screen.Holdings -> Text("Holdings Screen")
                Screen.Profile -> Text("Profile Screen")
            }
        }
        NavigationBar(appState)
    }
}