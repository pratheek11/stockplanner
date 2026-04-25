package com.example.stockplanner.utils.models

import com.example.stockplanner.utils.services.ApiService
import com.example.stockplanner.utils.services.HttpClientFactory
import com.example.stockplanner.utils.services.Quote
import com.example.stockplanner.utils.services.StockList
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.double
import kotlinx.serialization.json.int
import kotlinx.serialization.json.jsonPrimitive

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

data class Holdings(
    val invested: Number,
    val current: Number,
)

data class HoldingItem(
    val invested: Number,
    val current: Number,
    val quantityInStock: Int,
    val investedTradedAmount: Double,
    val currentTradedAmount: Double,
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

data class Candle(
    val timestamp: String,
    val open: Double,
    val high: Double,
    val low: Double,
    val close: Double,
    val volume: Int
)

fun List<JsonElement>.toCandle() = Candle(
    timestamp = this[0].jsonPrimitive.content,
    open      = this[1].jsonPrimitive.double,
    high      = this[2].jsonPrimitive.double,
    low       = this[3].jsonPrimitive.double,
    close     = this[4].jsonPrimitive.double,
    volume    = this[5].jsonPrimitive.int
)

class AppState {
    private val client = HttpClientFactory.create()
    private val apiService = ApiService(client)
    private val _isLoggedIn = MutableStateFlow(true)
    val isLoggedIn: StateFlow<Boolean> = _isLoggedIn
    var userName: String = "";
    var password: String = "";
    private val _currentScreen = MutableStateFlow<Screen>(Screen.Charts)
    val currentScreen: StateFlow<Screen> = _currentScreen
    private val _currentChartScreen = MutableStateFlow<ChartScreen>(ChartScreen.ChartList)
    val currentChartScreen: StateFlow<ChartScreen> = _currentChartScreen
    private val _currentFullChartItem = MutableStateFlow<ChartHeaderList>(ChartHeaderList("",""))
    val currentFullChartItem: StateFlow<ChartHeaderList> = _currentFullChartItem
    private val _currentList = MutableStateFlow<ChartHeaderList>(ChartHeaderList("List 1", "11"));
    val currentList: StateFlow<ChartHeaderList> = _currentList
    private val  _holdings = MutableStateFlow<Holdings>(Holdings(0, 0));
    val holdings: StateFlow<Holdings> = _holdings
    private val _currentPositions = MutableStateFlow<ArrayList<HoldingItem>>(ArrayList())
    val currentPositions: StateFlow<ArrayList<HoldingItem>> = _currentPositions

    val appNavigationButtons = listOf(
        NavigationButtons("Charts", Screen.Charts),
        NavigationButtons("Holdings", Screen.Holdings),
        NavigationButtons("Profile", Screen.Profile)
    )

    val chartItemByList = HashMap<String, ArrayList<ChartList>>();
    private val _quote = MutableStateFlow<Quote?>(null)
    val quote: StateFlow<Quote?> = _quote

    private val _stockList = MutableStateFlow<ArrayList<StockList>>(ArrayList<StockList>())
    val stockList: StateFlow<ArrayList<StockList>> = _stockList

    constructor() {
        setChartList("11")
        insertItemIntoChartList("11", ChartList(label = "MODINSU", id = "MODINSU", lastPrice = 1))
        insertItemIntoChartList("11", ChartList(label = "MOTHERSON", id = "MOTHERSON", lastPrice = 1))
        insertItemIntoChartList("11", ChartList(label = "NIFTYBEES", id = "NIFTYBEES", lastPrice = 1))
        insertItemIntoChartList("11", ChartList(label = "ATHERENE", id = "ATHERENE", lastPrice = 1))
        insertItemIntoChartList("11", ChartList(label = "ITC", id = "ITC", lastPrice = 1))
    }

    fun loadQuote() {
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val result = apiService.getQuote()
                _quote.value = result
            } catch (e: Exception) {
                println("API ERROR: ${e.message}") // 👈 important
            }
        }
    }

    suspend fun loadStockList() {
        try {
            _stockList.value = apiService.loadAndParse()
        } catch (e: Exception) {
            println("API ERROR: ${e.message}")
        }
    }

    fun loadChartData(instrumentKey: String) {
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val result = apiService.getFullChartData(instrumentKey)
                val candles = result.data.candles.map { it.toCandle() }
                println("API RESULT: $candles")
            } catch (e: Exception) {
                println("API ERROR: ${e.message}") // 👈 important
            }
        }
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

    fun setHoldings(invested: Number, current: Number) {
        _holdings.value = Holdings(invested, current)
    }

    fun setCurrentFullChart(id: String, label: String) {
        _currentFullChartItem.value = ChartHeaderList(id, label)
    }
}