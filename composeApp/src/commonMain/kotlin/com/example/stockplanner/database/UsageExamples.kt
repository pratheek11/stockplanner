package com.example.stockplanner.database

/**
 * DATABASE USAGE EXAMPLES FOR APPVIEWMODEL
 * 
 * This file demonstrates how to use the database in your app
 */

// ========== STEP 1: INITIALIZE DATABASE ==========
/*
In your main App or MainActivity setup:

fun initializeApp(factory: DatabaseFactory) {
    AppDatabaseRepository.initialize(factory)
    println("✓ Database initialized successfully")
}

// For Android, call with:
val factory = DatabaseFactory(applicationContext)
initializeApp(factory)

// For other platforms:
val factory = DatabaseFactory()
initializeApp(factory)
*/

// ========== STEP 2: USE DATABASE IN APPVIEWMODEL ==========
/*
class AppState {
    // Get database manager
    private val dbManager = getDatabaseManager()
    
    // WATCHLIST OPERATIONS
    fun addToWatchlist(symbol: String, name: String, price: Double) {
        dbManager.saveWatchlistItem(symbol, name, price)
        // Optionally reload UI
        loadWatchlistItems()
    }
    
    fun removeFromWatchlist(symbol: String) {
        dbManager.removeFromWatchlist(symbol)
    }
    
    private fun loadWatchlistItems() {
        CoroutineScope(databaseDispatcher).launch {
            val items = dbManager.getAllWatchlistItems()
            // Update your StateFlow
            // _watchlistItems.value = items
        }
    }
    
    // PORTFOLIO OPERATIONS
    fun addPortfolioHolding(
        symbol: String,
        name: String,
        quantity: Int,
        purchasePrice: Double,
        purchaseDate: String
    ) {
        dbManager.savePortfolioHolding(symbol, name, quantity, purchasePrice, purchaseDate)
        loadPortfolioHoldings()
    }
    
    fun sellPartially(symbol: String, newQuantity: Int) {
        dbManager.updateHoldingQuantity(symbol, newQuantity)
        loadPortfolioHoldings()
    }
    
    fun removeHolding(symbol: String) {
        dbManager.deletePortfolioHolding(symbol)
        loadPortfolioHoldings()
    }
    
    private fun loadPortfolioHoldings() {
        CoroutineScope(databaseDispatcher).launch {
            val holdings = dbManager.getAllPortfolioHoldings()
            // Update your StateFlow
            // _portfolioItems.value = holdings
        }
    }
    
    // USER PREFERENCES
    fun setUserPreference(key: String, value: String) {
        dbManager.saveUserPreference(key, value)
    }
    
    fun getTheme(): String? {
        return dbManager.getUserPreference("theme")
    }
}
*/

// ========== STEP 3: CREATE YOUR SQL SCHEMA ==========
/*
Create a file: composeApp/src/commonMain/sqldelight/com/example/stockplanner/db/AppDatabase.sq

Example schema:

-- Watchlist table
CREATE TABLE watchlist (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    symbol TEXT NOT NULL UNIQUE,
    name TEXT NOT NULL,
    currentPrice REAL NOT NULL,
    addedDate INTEGER NOT NULL
);

-- Get all watchlist items
selectAllWatchlist:
SELECT * FROM watchlist;

-- Insert into watchlist
insertWatchlist:
INSERT INTO watchlist(symbol, name, currentPrice, addedDate)
VALUES (?, ?, ?, ?);

-- Delete from watchlist
deleteWatchlistBySymbol:
DELETE FROM watchlist WHERE symbol = ?;

---

-- Portfolio table
CREATE TABLE portfolio_holdings (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    symbol TEXT NOT NULL UNIQUE,
    name TEXT NOT NULL,
    quantity INTEGER NOT NULL,
    purchasePrice REAL NOT NULL,
    purchaseDate TEXT NOT NULL
);

-- Get all portfolio holdings
selectAllPortfolioHoldings:
SELECT * FROM portfolio_holdings;

-- Insert portfolio holding
insertPortfolioHolding:
INSERT INTO portfolio_holdings(symbol, name, quantity, purchasePrice, purchaseDate)
VALUES (?, ?, ?, ?, ?);

-- Update quantity
updatePortfolioQuantity:
UPDATE portfolio_holdings SET quantity = ? WHERE symbol = ?;

-- Delete portfolio holding
deletePortfolioBySymbol:
DELETE FROM portfolio_holdings WHERE symbol = ?;

---

-- User preferences table
CREATE TABLE user_preferences (
    key TEXT PRIMARY KEY,
    value TEXT NOT NULL
);

-- Get preference
getUserPreference:
SELECT value FROM user_preferences WHERE key = ?;

-- Save preference
insertUserPreference:
INSERT OR REPLACE INTO user_preferences(key, value)
VALUES (?, ?);
*/

// ========== STEP 4: USE IN COMPOSABLES ==========
/*
@Composable
fun WatchlistScreen(viewModel: AppState) {
    LaunchedEffect(Unit) {
        viewModel.loadWatchlistItems()
    }
    
    Button(onClick = {
        viewModel.addToWatchlist("INFY", "Infosys", 1500.0)
    }) {
        Text("Add to Watchlist")
    }
}
*/

// ========== KEY POINTS ==========
/*
1. Always use databaseDispatcher for database operations
2. Run database ops in background (Dispatchers.IO)
3. Update StateFlow on Main thread after DB operations
4. Create your .sq SQL files with proper queries
5. SQLDelight auto-generates query functions from your .sq files
6. Uncomment the database calls once your schema is defined

Example dispatcher usage:
    CoroutineScope(databaseDispatcher).launch {
        val data = getDatabaseManager().getAllWatchlistItems()
        withContext(Dispatchers.Main) {
            _items.value = data
        }
    }
*/
