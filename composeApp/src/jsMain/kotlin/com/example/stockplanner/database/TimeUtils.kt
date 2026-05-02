package com.example.stockplanner.database

actual fun getCurrentTimeMillis(): Long {
    return js("Date.now()").toString().toLong()
}
