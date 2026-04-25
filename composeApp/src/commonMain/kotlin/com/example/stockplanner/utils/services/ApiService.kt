package com.example.stockplanner.utils.services

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.datetime.LocalDate
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlin.time.Clock
import org.jetbrains.compose.resources.ExperimentalResourceApi
import stockplanner.composeapp.generated.resources.Res

@Serializable
data class Quote(
    val id: Int,
    val quote: String,
    val author: String
)

@Serializable
data class CandleResponse(
    val status: String,
    val data: CandleData
)

@Serializable
data class StockList(
    val segment: String,
    val name: String,
    val exchange: String,
    val isin: String? = null,  // nullable with default
    val instrument_type: String,
    val instrument_key: String,
    val lot_size: Int? = null,
    val freeze_quantity: Double? = null,
    val exchange_token: String,
    val tick_size: Double? = null,
    val trading_symbol: String,
    val qty_multiplier: Double? = null
)

@Serializable
data class CandleData(
    val candles: List<List<JsonElement>>
)

class ApiService(private val client: HttpClient) {

    private val json = Json { ignoreUnknownKeys = true }

    suspend fun getUsers() {
        return client.get("https://jsonplaceholder.typicode.com/users").body()
    }

    suspend fun getQuote(): Quote {
        return client.get("https://dummyjson.com/quotes/random").body()
    }

    @OptIn(ExperimentalResourceApi::class)
    suspend fun loadAndParse(): ArrayList<StockList> = withContext(Dispatchers.Default) {
        val jsonString = Res.readBytes("files/nse.json").decodeToString()
        json.decodeFromString<ArrayList<StockList>>(jsonString)
    }

    suspend fun getFullChartData(instrumentKey: String): CandleResponse {
        val (from, to) = getDateRange(30)
        return client.get(
            "https://api.upstox.com/v3/historical-candle/$instrumentKey/days/1/$to/$from"
        ).body()
    }

    fun getDateRange(days: Int): Pair<String, String> {
        val instant = Clock.System.now()
        val epochDays = (instant.toEpochMilliseconds() / 86_400_000L).toInt()
        val today = LocalDate.fromEpochDays(epochDays)
        val pastDate = LocalDate.fromEpochDays(epochDays - days)
        return pastDate.toString() to today.toString()
    }
}