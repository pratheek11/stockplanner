package com.example.stockplanner.utils.models

import com.example.stockplanner.components.Alerts
import com.example.stockplanner.database.AppDatabaseRepository
import com.example.stockplanner.database.DatabaseManager
import com.example.stockplanner.database.databaseDispatcher
import com.example.stockplanner.utils.services.ApiService
import com.example.stockplanner.utils.services.HttpClientFactory
import com.example.stockplanner.utils.services.Quote
import com.example.stockplanner.utils.services.StockList
import com.example.stockplanner.db.AppDatabase
import com.example.stockplanner.db.Watchlist
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.double
import kotlinx.serialization.json.int
import kotlinx.serialization.json.jsonPrimitive
import kotlin.time.Clock
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

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

class AppState(
    private val dbManager: DatabaseManager? = null,
) {
    private val client = HttpClientFactory.create()
    private val apiService = ApiService(client)
    
    // Database instance
    private val database: AppDatabase
        get() = AppDatabaseRepository.getInstance()
    
    private val _isLoggedIn = MutableStateFlow(false)
    val isLoggedIn: StateFlow<Boolean> = _isLoggedIn
    var userName: String = "";
    var email: String = "";
    private val _currentScreen = MutableStateFlow<Screen>(Screen.Charts)
    val currentScreen: StateFlow<Screen> = _currentScreen
    private val _currentChartScreen = MutableStateFlow<ChartScreen>(ChartScreen.ChartList)
    val currentChartScreen: StateFlow<ChartScreen> = _currentChartScreen
    private val _chatHeaderList = MutableStateFlow<List<ChartHeaderList>>(ArrayList<ChartHeaderList>());
    val chartHeaderList: StateFlow<List<ChartHeaderList>> = _chatHeaderList
    private val _currentList = MutableStateFlow<ChartHeaderList?>(null)
    val currentList: StateFlow<ChartHeaderList?> = _currentList
    private val  _holdings = MutableStateFlow<Holdings>(Holdings(0, 0));
    val holdings: StateFlow<Holdings> = _holdings
    private val _currentPositions = MutableStateFlow<ArrayList<HoldingItem>>(ArrayList())
    val currentPositions: StateFlow<ArrayList<HoldingItem>> = _currentPositions

    private val _currentFullChart = MutableStateFlow<Watchlist?>(null)
    val currentFullChart: StateFlow<Watchlist?> = _currentFullChart

    val appNavigationButtons = listOf(
        NavigationButtons("Charts", Screen.Charts),
        NavigationButtons("Holdings", Screen.Holdings),
        NavigationButtons("Profile", Screen.Profile)
    )

    private val _chartItemByList = MutableStateFlow<HashMap<String, List<Watchlist>>>(HashMap())
    val chartItemByList: StateFlow<HashMap<String, List<Watchlist>>> = _chartItemByList
    private val _quote = MutableStateFlow<Quote?>(null)
    val quote: StateFlow<Quote?> = _quote

    private val _stockList = MutableStateFlow<ArrayList<StockList>>(ArrayList<StockList>())
    val stockList: StateFlow<ArrayList<StockList>> = _stockList

    private var searchJob: Job? = null
    private val _searchResults = MutableStateFlow<List<StockList>>(ArrayList<StockList>())
    val searchResults: StateFlow<List<StockList>> = _searchResults
    private val _uiMessage = MutableStateFlow<String?>(null)
    val uiMessage: StateFlow<String?> = _uiMessage

    fun clearMessage() {
        _uiMessage.value = null
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

    // ========== DATABASE OPERATIONS ==========
    
    /**
     * Save a watchlist item to database
     * Note: Adjust this based on your actual AppDatabase schema
     */
    fun saveWatchlistItem(symbol: String, name: String, price: Double) {
        CoroutineScope(databaseDispatcher).launch {
            try {
//                dbManager?.saveWatchlistItem(symbol, name, price)
                println("Watchlist item saved: $symbol")
            } catch (e: Exception) {
                println("Database ERROR: ${e.message}")
            }
        }
    }

    /**
     * Load all watchlist items from database
     */
    fun loadWatchlistItems() {
        CoroutineScope(databaseDispatcher).launch {
            try {
                // Example: val items = database.appQueries.selectAllWatchlist().executeAsList()
                // Then update a StateFlow with the items
                println("Loaded watchlist items from database")
            } catch (e: Exception) {
                println("Database ERROR: ${e.message}")
            }
        }
    }

    /**
     * Delete a watchlist item from database
     */
    fun deleteWatchlistItem(symbol: String) {
        CoroutineScope(databaseDispatcher).launch {
            try {
                // Example: database.appQueries.deleteWatchlistItem(symbol)
                println("Watchlist item deleted: $symbol")
            } catch (e: Exception) {
                println("Database ERROR: ${e.message}")
            }
        }
    }

    /**
     * Save portfolio holding to database
     */
    fun savePortfolioHolding(symbol: String, quantity: Int, purchasePrice: Double, purchaseDate: String) {
        CoroutineScope(databaseDispatcher).launch {
            try {
                // Example: database.appQueries.insertPortfolioHolding(symbol, quantity, purchasePrice, purchaseDate)
                println("Portfolio holding saved: $symbol - Qty: $quantity")
            } catch (e: Exception) {
                println("Database ERROR: ${e.message}")
            }
        }
    }

    /**
     * Load all portfolio holdings from database
     */
    fun loadPortfolioHoldings() {
        CoroutineScope(databaseDispatcher).launch {
            try {
                // Example: val holdings = database.appQueries.selectAllPortfolioHoldings().executeAsList()
                // Then update StateFlow to refresh UI
                println("Loaded portfolio holdings from database")
            } catch (e: Exception) {
                println("Database ERROR: ${e.message}")
            }
        }
    }

    /**
     * Update portfolio holding quantity
     */
    fun updatePortfolioHolding(symbol: String, newQuantity: Int) {
        CoroutineScope(databaseDispatcher).launch {
            try {
                // Example: database.appQueries.updatePortfolioHoldingQuantity(newQuantity, symbol)
                println("Portfolio holding updated: $symbol - New Qty: $newQuantity")
            } catch (e: Exception) {
                println("Database ERROR: ${e.message}")
            }
        }
    }

    /**
     * Delete portfolio holding
     */
    fun deletePortfolioHolding(symbol: String) {
        CoroutineScope(databaseDispatcher).launch {
            try {
                // Example: database.appQueries.deletePortfolioHolding(symbol)
                println("Portfolio holding deleted: $symbol")
            } catch (e: Exception) {
                println("Database ERROR: ${e.message}")
            }
        }
    }


    fun getByKey(key: String) {
        searchJob?.cancel()
        searchJob = CoroutineScope(Dispatchers.Default).launch {
            delay(300) // wait 300ms after last keystroke
            if (key.isBlank() || key.length < 3) {
                _searchResults.value = emptyList()
                return@launch
            }
            _searchResults.value = _stockList.value.filter { ele ->
               (ele.name.contains(key, ignoreCase = true) || ele.trading_symbol.contains(key, ignoreCase = true))&& (ele.segment.contains("NSE_EQ") || ele.segment.contains("BSE_EQ"))
            }
                .sortedBy { it.name }
        }
    }


    fun login(userName: String, password: String) {
        if (userName.isNotBlank() && password.isNotBlank()) {
            val userCreds = dbManager?.getUserDetails(userName, password)
            if (userCreds != null) {
                _isLoggedIn.value = true
                this.userName = userCreds.userName
                this.email = userCreds.email
            } else {
                _uiMessage.value = "Invalid username or password"
            }
        }
    }

    fun logout() {
        _isLoggedIn.value = false
    }

    fun signup(userName: String, password: String, email: String) {
        if (userName.isNotBlank() && password.isNotBlank()) {
            val userCreds = dbManager?.signUserDetails(userName, password, email)
            if(userCreds != null && userCreds.contains("Success")) {
                _isLoggedIn.value = true
                this.userName = userName
                this.email = email
            } else {
                _uiMessage.value = "Can't Sign up"
            }
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

    fun setCurrentList(label: ChartHeaderList) {
        _currentList.value = label
    }

    fun setCurrentFullChart(item: Watchlist) {
        _currentFullChart.value = item
    }

    fun setCurrentFullChart(item: StockList) {
        _currentFullChart.value = Watchlist(1, currentList.value?.id + "", item.trading_symbol, item.name, item.exchange,
            Clock.System.now().epochSeconds)
    }

    fun deleteUserWatchList() {
        dbManager?.deleteUserWatchlist(userName)
    }

    fun getChartHeaderList() {
        val list = dbManager?.getUserWatchlist(userName)
        list?.forEach { watchlist ->
            var list = emptyList<ChartHeaderList>()
            val newEle = ChartHeaderList(watchlist.label, watchlist.watchListId)
            list = list.plus(newEle)
            _chatHeaderList.value = list
        }
    }

    @OptIn(ExperimentalUuidApi::class)
    fun addChartHeaderList(label: String) {
        var list = _chatHeaderList.value
        val newEle = ChartHeaderList(label, Uuid.generateV4().toString())
        list = list.plus(newEle)
        _chatHeaderList.value = list
        dbManager?.insertUserWatchlist(userName, newEle.label, newEle.id)
    }

    fun setChartList(listName: String) {
        val newMap = HashMap(_chartItemByList.value)
        newMap[listName] = ArrayList()
        _chartItemByList.value = newMap
    }

    fun insertItemIntoChartList(listName: String, items: StockList) {
        addElementToChartList(listName, items)
        dbManager?.insertItemIntoChartList(listName, items)
    }

    fun addElementToChartList(listName: String, items: StockList) {
        val newMap = HashMap(_chartItemByList.value)
        if(newMap.isNotEmpty() && newMap[listName].isNullOrEmpty()) {
            newMap[listName] = ArrayList()
        }
        val newList = newMap[listName]?.plus(Watchlist(1,listName, items.trading_symbol, items.name, items.exchange,
            Clock.System.now().epochSeconds))
        if(!newList.isNullOrEmpty()) {
            newMap[listName] = newList
        }
        _chartItemByList.value = newMap
    }

    fun getItemFromChartListByList(listName: String) {
        val list = dbManager?.getItemFromChartListByList(listName)
        val newMap = HashMap(_chartItemByList.value)
        if(newMap.isNotEmpty() && newMap[listName].isNullOrEmpty()) {
            newMap[listName] = ArrayList()
        }
        if(!list.isNullOrEmpty()) newMap[listName] = list
        _chartItemByList.value = newMap
    }

    fun removeChartList(listName: String) {
        _chartItemByList.value.remove(listName)
    }

    fun setHoldings(invested: Number, current: Number) {
        _holdings.value = Holdings(invested, current)
    }
}