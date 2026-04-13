package com.example.stockplanner

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform