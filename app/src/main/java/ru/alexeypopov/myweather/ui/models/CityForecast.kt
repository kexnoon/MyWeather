package ru.alexeypopov.myweather.ui.models

data class CityWeather(
    val cityName: String?,
    val dateTime: String?,
    val iconUrl: String?,
    val temp: String?,
    val conditionDesc: String?,
    val forecast: List<CityWeatherForecastItem>?
)

data class CityWeatherForecastItem(
    val dayOfWeek: String?,
    val icon: String?,
    val tempDay: String?,
    val tempNight: String?
)
