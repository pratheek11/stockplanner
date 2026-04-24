package com.example.stockplanner.utils.services

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import kotlinx.serialization.Serializable

@Serializable
data class Quote(
    val id: Int,
    val quote: String,
    val author: String
)

class ApiService(private val client: HttpClient) {

    suspend fun getUsers() {
        return client.get("https://jsonplaceholder.typicode.com/users").body()
    }

    suspend fun getQuote(): Quote {
        return client.get("https://dummyjson.com/quotes/random").body()
    }
}