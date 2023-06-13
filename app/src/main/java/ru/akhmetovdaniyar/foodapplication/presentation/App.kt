package ru.akhmetovdaniyar.foodapplication.presentation

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import ru.akhmetovdaniyar.foodapplication.presentation.di.appModule
import ru.akhmetovdaniyar.foodapplication.presentation.di.dataModule

class App: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@App)
            modules(listOf(appModule, dataModule))
        }
    }
}