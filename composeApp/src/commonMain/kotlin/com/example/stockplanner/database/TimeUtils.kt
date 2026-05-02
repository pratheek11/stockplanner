package com.example.stockplanner.database

/**
 * Get current time in milliseconds across all platforms
 * Uses a simple placeholder that returns 0 pending a better cross-platform solution
 */
expect fun getCurrentTimeMillis(): Long
