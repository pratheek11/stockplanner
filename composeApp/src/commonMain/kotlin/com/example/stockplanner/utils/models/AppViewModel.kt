package com.example.stockplanner.utils.models

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

data class NavigationButtons(
    val label: String,
    val screen: Screen
)

data class ChartHeaderList(
    val label: String,
    val id: String
)

sealed class Screen {
    object Charts : Screen()
    object Holdings : Screen()
    object Profile : Screen()
}

class AppState {

    private val _isLoggedIn = MutableStateFlow(true)
    val isLoggedIn: StateFlow<Boolean> = _isLoggedIn
    var userName: String = "";
    var password: String = "";
    private val _currentScreen = MutableStateFlow<Screen>(Screen.Charts)
    val currentScreen: StateFlow<Screen> = _currentScreen
    private val _currentList = MutableStateFlow<ChartHeaderList>(ChartHeaderList("List 1", "11"));
    val currentList: StateFlow<ChartHeaderList> = _currentList

    val appNavigationButtons = listOf(
        NavigationButtons("Charts", Screen.Charts),
        NavigationButtons("Holdings", Screen.Holdings),
        NavigationButtons("Profile", Screen.Profile)
    )
    fun login(userName: String, password: String) {
        if (userName.isNotBlank() && password.isNotBlank()) {
            _isLoggedIn.value = true
            this.userName = userName
            this.password = password
        }
    }

    fun logout() {
        _isLoggedIn.value = false
    }

    fun signup(userName: String, password: String) {
        if (userName.isNotBlank() && password.isNotBlank()) {
            _isLoggedIn.value = true
            this.userName = userName
            this.password = password
        }
    }

    fun signout() {
        _isLoggedIn.value = false
    }

    fun setCurrentScreen(screen: Screen) {
        _currentScreen.value = screen
    }

    fun setCurrentList(list: ChartHeaderList) {
        _currentList.value = list
    }
}