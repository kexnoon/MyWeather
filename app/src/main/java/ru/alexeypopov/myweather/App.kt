package ru.alexeypopov.myweather

import android.app.Application

class App: Application() {
    companion object {
        const val DEFAULT_CITY = "Moscow"
        const val DEFAULT_FORECAST_DAYS = 5
    }
}