package com.example.initializer

import android.content.Context
import android.util.Log
import androidx.startup.Initializer
import kotlin.jvm.java

class AppInitializer : Initializer<Unit> {

    override fun create(context: Context) {
        Log.d(TAG, "Dependencies initialized.")
    }

    override fun dependencies(): List<Class<out Initializer<*>>> = listOf(
        KoinInitializer::class.java,
    )

    companion object {
        private const val TAG = "AppInitializer"
    }
}