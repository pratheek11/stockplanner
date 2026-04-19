package com.example.stockplanner.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun Home(

) {
    Column(
        modifier = Modifier.safeContentPadding()
    ) {
        MenuBar()
        Text("Welcome to Home Screen")
    }
}