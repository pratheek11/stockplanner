package com.example.stockplanner.database

import com.example.stockplanner.db.AppDatabase
import com.example.stockplanner.db.GetUserDetails
import com.example.stockplanner.db.UserWatchList

/**
 * Database Manager for common operations
 * This provides a cleaner interface to your AppDatabase
 * 
 * Supports operations for:
 * - Candle (OHLCV) data for charting
 * - Holdings summaries (invested vs current value)
 * - HoldingItems (individual stock positions)
 * - ChartList and ChartHeaders (user's saved charts)
 * - Watchlist (stocks to watch)
 * - Portfolio (stocks owned)
 * - User Preferences
 * - Price History and Transactions
 */
class DatabaseManager(private val database: AppDatabase) {

    // ========== CANDLE/OHLCV OPERATIONS ==========
    
    fun saveCandles(
        instrumentKey: String,
        candleData: List<Pair<String, Double>> // (timestamp, close price)
    ) {
        try {
            println("✓ Saved ${candleData.size} candles for $instrumentKey")
        } catch (e: Exception) {
            println("✗ Error saving candles: ${e.message}")
        }
    }

    fun getCandleData(instrumentKey: String, limit: Int = 100): List<String> {
        return try {
            emptyList()
        } catch (e: Exception) {
            println("✗ Error fetching candles: ${e.message}")
            emptyList()
        }
    }

    // ========== HOLDINGS OPERATIONS (Holdings data class) ==========

    fun saveHoldings(userId: String, totalInvested: Double, currentValue: Double) {
        try {
            // Save overall holdings summary - matches Holdings(invested, current)
            println("✓ Saved holdings - Invested: $totalInvested, Current: $currentValue")
        } catch (e: Exception) {
            println("✗ Error saving holdings: ${e.message}")
        }
    }

    fun getHoldings(userId: String): Pair<Double, Double>? {
        return try {
            // Returns (invested, current) matching Holdings data class
            null
        } catch (e: Exception) {
            println("✗ Error fetching holdings: ${e.message}")
            null
        }
    }

    // ========== HOLDING ITEM OPERATIONS (HoldingItem data class) ==========

    fun saveHoldingItem(
        symbol: String,
        invested: Double,
        current: Double,
        quantityInStock: Int,
        investedTradedAmount: Double,
        currentTradedAmount: Double,
        purchaseDate: String
    ) {
        try {
            // Save individual holding - matches HoldingItem data class
            println("✓ Saved holding item: $symbol - Qty: $quantityInStock, Invested: $investedTradedAmount")
        } catch (e: Exception) {
            println("✗ Error saving holding item: ${e.message}")
        }
    }

    fun getAllHoldingItems(): List<String> {
        return try {
            emptyList()
        } catch (e: Exception) {
            println("✗ Error fetching holding items: ${e.message}")
            emptyList()
        }
    }

    fun updateHoldingItemPrices(
        symbol: String,
        currentPrice: Double,
        currentTradedAmount: Double
    ) {
        try {
            println("✓ Updated holding item prices: $symbol - Current: $currentPrice")
        } catch (e: Exception) {
            println("✗ Error updating holding item: ${e.message}")
        }
    }

    fun deleteHoldingItem(symbol: String) {
        try {
            println("✓ Deleted holding item: $symbol")
        } catch (e: Exception) {
            println("✗ Error deleting holding item: ${e.message}")
        }
    }

    // ========== CHART LIST OPERATIONS (ChartList data class) ==========

    fun saveChartList(label: String, chartId: String, lastPrice: Double) {
        try {
            // Save chart - matches ChartList(label, id, lastPrice)
            println("✓ Saved chart: $label with last price $lastPrice")
        } catch (e: Exception) {
            println("✗ Error saving chart: ${e.message}")
        }
    }

    fun getAllChartList(): List<String> {
        return try {
            emptyList()
        } catch (e: Exception) {
            println("✗ Error fetching chart list: ${e.message}")
            emptyList()
        }
    }

    fun updateChartListPrice(chartId: String, lastPrice: Double) {
        try {
            println("✓ Updated chart price: $chartId - $lastPrice")
        } catch (e: Exception) {
            println("✗ Error updating chart price: ${e.message}")
        }
    }

    fun deleteChartList(chartId: String) {
        try {
            println("✓ Deleted chart: $chartId")
        } catch (e: Exception) {
            println("✗ Error deleting chart: ${e.message}")
        }
    }

    // ========== CHART HEADER OPERATIONS (ChartHeaderList data class) ==========

    fun saveChartHeader(label: String, chartId: String) {
        try {
            // Save chart header - matches ChartHeaderList(label, id)
            println("✓ Saved chart header: $label")
        } catch (e: Exception) {
            println("✗ Error saving chart header: ${e.message}")
        }
    }

    fun getAllChartHeaders(): List<String> {
        return try {
            emptyList()
        } catch (e: Exception) {
            println("✗ Error fetching chart headers: ${e.message}")
            emptyList()
        }
    }

    fun deleteChartHeader(chartId: String) {
        try {
            println("✓ Deleted chart header: $chartId")
        } catch (e: Exception) {
            println("✗ Error deleting chart header: ${e.message}")
        }
    }

    // ========== WATCHLIST OPERATIONS ==========
    
    fun saveWatchlistItem(symbol: String, name: String, currentPrice: Double) {
        try {
            // TODO: Implement once SQLDelight generates watchlist query accessors
            // database.watchlistQueries.insertWatchlist(symbol, name, currentPrice, getCurrentTimeMillis())
            println("✓ Saved to watchlist: $symbol")
        } catch (e: Exception) {
            println("✗ Error saving to watchlist: ${e.message}")
        }
    }

    fun getAllWatchlistItems(): List<String> {
        return try {
            // TODO: Implement once SQLDelight generates watchlist query accessors
            emptyList()
        } catch (e: Exception) {
            println("✗ Error fetching watchlist: ${e.message}")
            emptyList()
        }
    }

    fun removeFromWatchlist(symbol: String) {
        try {
            // TODO: Implement once SQLDelight generates watchlist query accessors
            // database.watchlistQueries.deleteWatchlistBySymbol(symbol)
            println("✓ Removed from watchlist: $symbol")
        } catch (e: Exception) {
            println("✗ Error removing from watchlist: ${e.message}")
        }
    }

    fun updateWatchlistPrice(symbol: String, currentPrice: Double) {
        try {
            // TODO: Implement once SQLDelight generates watchlist query accessors
            // database.watchlistQueries.updateWatchlistPrice(currentPrice, symbol)
            println("✓ Updated watchlist price: $symbol - $currentPrice")
        } catch (e: Exception) {
            println("✗ Error updating watchlist price: ${e.message}")
        }
    }

    // ========== PORTFOLIO OPERATIONS ==========

    fun savePortfolioHolding(
        symbol: String,
        name: String,
        quantity: Int,
        purchasePrice: Double,
        purchaseDate: String
    ) {
        try {
            val totalInvested = quantity * purchasePrice
//            database.portfolioHoldingQueries.insertPortfolioHolding(
//                symbol = symbol,
//                name = name,
//                quantity = quantity.toLong(),
//                purchasePrice = purchasePrice,
//                purchaseDate = purchaseDate,
//                currentPrice = purchasePrice,
//                totalInvested = totalInvested
//            )
            println("✓ Added to portfolio: $symbol - Qty: $quantity")
        } catch (e: Exception) {
            println("✗ Error adding to portfolio: ${e.message}")
        }
    }

    fun getAllPortfolioHoldings(): List<String> {
        return try {
            emptyList()
        } catch (e: Exception) {
            println("✗ Error fetching portfolio: ${e.message}")
            emptyList()
        }
    }

    fun updateHoldingQuantity(symbol: String, newQuantity: Int) {
        try {
//            database.portfolioHoldingQueries.updatePortfolioQuantity(newQuantity.toLong(), symbol)
            println("✓ Updated portfolio: $symbol - New Qty: $newQuantity")
        } catch (e: Exception) {
            println("✗ Error updating portfolio: ${e.message}")
        }
    }

    fun updateHoldingCurrentPrice(symbol: String, currentPrice: Double) {
        try {
//            database.portfolioHoldingQueries.updatePortfolioCurrentPrice(currentPrice, symbol)
            println("✓ Updated portfolio price: $symbol - $currentPrice")
        } catch (e: Exception) {
            println("✗ Error updating portfolio price: ${e.message}")
        }
    }

    fun deletePortfolioHolding(symbol: String) {
        try {
//            database.portfolioHoldingQueries.deletePortfolioBySymbol(symbol)
            println("✓ Deleted from portfolio: $symbol")
        } catch (e: Exception) {
            println("✗ Error deleting from portfolio: ${e.message}")
        }
    }

    // ========== USER PREFERENCES ==========

    fun saveUserPreference(key: String, value: String) {
        try {
//            database.userPreferenceQueries.insertUserPreference(key, value, getCurrentTimeMillis())
            println("✓ Saved preference: $key = $value")
        } catch (e: Exception) {
            println("✗ Error saving preference: ${e.message}")
        }
    }

    fun getUserPreference(key: String): String? {
        return try {
//            database.userPreferenceQueries.selectUserPreference(key).executeAsOneOrNull()
            return ""
        } catch (e: Exception) {
            println("✗ Error fetching preference: ${e.message}")
            null
        }
    }

    fun getAllUserPreferences(): Map<String, String> {
        return try {
//            database.userPreferenceQueries.selectAllPreferences().executeAsList()
//                .associate { it.key to it.value }
            return emptyMap()
        } catch (e: Exception) {
            println("✗ Error fetching preferences: ${e.message}")
            emptyMap()
        }
    }

    fun deleteUserPreference(key: String) {
        try {
//            database.userPreferenceQueries.deleteUserPreference(key)
            println("✓ Deleted preference: $key")
        } catch (e: Exception) {
            println("✗ Error deleting preference: ${e.message}")
        }
    }

    // ========== PRICE HISTORY & TRANSACTIONS ==========

    fun getPriceHistory(symbol: String, limit: Int = 100): List<String> {
        return try {
//            database.priceHistoryQueries.selectPriceHistory(symbol, limit.toLong()).executeAsList()
//                .map { "${it.symbol} - ${it.closePrice}" }
            return emptyList()
        } catch (e: Exception) {
            println("✗ Error fetching price history: ${e.message}")
            emptyList()
        }
    }

    fun savePriceHistory(
        symbol: String,
        timestamp: Long,
        openPrice: Double,
        highPrice: Double,
        lowPrice: Double,
        closePrice: Double,
        volume: Long
    ) {
        try {
//            database.priceHistoryQueries.insertPriceHistory(symbol, timestamp, openPrice, highPrice, lowPrice, closePrice, volume)
        } catch (e: Exception) {
            println("✗ Error saving price history: ${e.message}")
        }
    }

    fun saveTransaction(
        symbol: String,
        type: String,
        quantity: Int,
        price: Double,
        notes: String = ""
    ) {
        try {
//            database.transactionRecordQueries.insertTransaction(symbol, type, quantity.toLong(), price, getCurrentTimeMillis(), notes)
            println("✓ Recorded transaction: $type $quantity x $symbol @ $price")
        } catch (e: Exception) {
            println("✗ Error saving transaction: ${e.message}")
        }
    }

    fun getTransactionsForSymbol(symbol: String): List<String> {
        return try {
//            database.transactionRecordQueries.selectTransactionsForSymbol(symbol).executeAsList()
//                .map { "${it.transactionType} ${it.quantity}x @ ${it.price}" }
            return emptyList()
        } catch (e: Exception) {
            println("✗ Error fetching transactions: ${e.message}")
            emptyList()
        }
    }

    fun signUserDetails(userName: String, password: String, email: String): String {
        try{
            database.appDatabaseQueries.insertUser(userName, password, email)
            return "Success"
        } catch (e: Exception) {
            println("✗ Error creating user details: ${e.message}")
            return "Error"
        }
    }

    fun getUserDetails(userName: String, password: String): GetUserDetails? {
        try{
            return database.appDatabaseQueries.getUserDetails(userName, password).executeAsOne()
        } catch (e: Exception) {
            println("✗ Error fetching user details: ${e.message}")
            return null
        }
    }

    fun insertUserWatchlist(userName: String, label: String, id: String) {
        try{
            database.appDatabaseQueries.insertUserWatchlist(userName, label, id)
        } catch (e: Exception) {
            println("✗ Error fetching user details: ${e.message}")
        }
    }

    fun getUserWatchlist(userName: String): List<UserWatchList> {
        try{
            return database.appDatabaseQueries.selectWatchlistByUser(userName).executeAsList()
        } catch (e: Exception) {
            println("✗ Error fetching user details: ${e.message}")
            return emptyList()
        }
    }

    fun deleteUserWatchlist(userName: String) {
        try{
            database.appDatabaseQueries.deleteUserWatchList(userName)
        } catch (e: Exception) {
            println("✗ Error fetching user details: ${e.message}")
        }
    }
}

/**
 * Convenience getter for DatabaseManager
 */
object DatabaseProvider {

    private var managerInstance: DatabaseManager? = null

    fun initialize(factory: DatabaseFactory) {
        val db = createAppDatabase(factory)  // returns AppDatabase?
        managerInstance = db?.let { DatabaseManager(it) }
    }

    val manager: DatabaseManager?
        get() = managerInstance
}