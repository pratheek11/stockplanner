package com.example.stockplanner.database

import kotlin.time.Clock

actual fun getCurrentTimeMillis(): Long {
    // TODO: Implement with proper iOS time function
    // Placeholder returning 0 for now
    return Clock.System.now().toEpochMilliseconds()
}
