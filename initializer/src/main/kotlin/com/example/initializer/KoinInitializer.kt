package com.example.initializer

import android.content.Context
import androidx.startup.Initializer
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class KoinInitializer : Initializer<Unit> {

    override fun create(context: Context) {
        startKoin {
            androidContext(context.applicationContext)
            modules(
                listOf()
            )
        }
    }

    override fun dependencies(): List<Class<out Initializer<*>>> = emptyList()
}