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

data class ChartList(
    val label: String,
    val id: String,
    val lastPrice: Number
)

sealed class Screen {
    object Charts : Screen()
    object Holdings : Screen()
    object Profile : Screen()
}

sealed class ChartScreen {
    object FullChart : ChartScreen()
    object ChartList : ChartScreen()

}

class AppState {

    private val _isLoggedIn = MutableStateFlow(true)
    val isLoggedIn: StateFlow<Boolean> = _isLoggedIn
    var userName: String = "";
    var password: String = "";
    private val _currentScreen = MutableStateFlow<Screen>(Screen.Charts)
    val currentScreen: StateFlow<Screen> = _currentScreen
    private val _currentChartScreen = MutableStateFlow<ChartScreen>(ChartScreen.ChartList)
    val currentChartScreen: StateFlow<ChartScreen> = _currentChartScreen
    private val _currentList = MutableStateFlow<ChartHeaderList>(ChartHeaderList("List 1", "11"));
    val currentList: StateFlow<ChartHeaderList> = _currentList

    val appNavigationButtons = listOf(
        NavigationButtons("Charts", Screen.Charts),
        NavigationButtons("Holdings", Screen.Holdings),
        NavigationButtons("Profile", Screen.Profile)
    )

    val chartItemByList = HashMap<String, ArrayList<ChartList>>();

    constructor() {
        setChartList("11")
        insertItemIntoChartList("11", ChartList(label = "MODINSU", id = "MODINSU", lastPrice = 1))
        insertItemIntoChartList("11", ChartList(label = "MOTHERSON", id = "MOTHERSON", lastPrice = 1))
        insertItemIntoChartList("11", ChartList(label = "NIFTYBEES", id = "NIFTYBEES", lastPrice = 1))
        insertItemIntoChartList("11", ChartList(label = "ATHERENE", id = "ATHERENE", lastPrice = 1))
        insertItemIntoChartList("11", ChartList(label = "ITC", id = "ITC", lastPrice = 1))
    }

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

    fun setChartScreen(screen: ChartScreen) {
        _currentChartScreen.value = screen
    }

    fun setCurrentList(list: ChartHeaderList) {
        _currentList.value = list
    }

    fun setChartList(listName: String) {
        chartItemByList[listName] = ArrayList()
    }

    fun insertItemIntoChartList(listName: String, item: ChartList) {
        chartItemByList[listName]?.add(item)
    }

    fun removeChartList(listName: String) {
        chartItemByList.remove(listName)
    }
}