package com.example.stockplanner

import android.content.Context
import android.os.Build

lateinit var appContext: Context

class AndroidPlatform : Platform {
    override val name: String = "Android ${Build.VERSION.SDK_INT}"
}

actual fun getPlatform(): Platform = AndroidPlatform()