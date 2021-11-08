package ru.alexeypopov.myweather.repository

import android.annotation.SuppressLint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.alexeypopov.myweather.common.UIModel
import ru.alexeypopov.myweather.services.WeatherService
import ru.alexeypopov.myweather.ui.models.CityWeather
import ru.alexeypopov.myweather.ui.models.CityWeatherForecastItem
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.time.format.TextStyle
import java.util.*

class WeatherRepository(private val weatherService: WeatherService) {

    @SuppressLint("WeekBasedYear")
    suspend fun getWeatherForecast(
        cityName: String,
        days: Int
    ): UIModel<CityWeather> = withContext(Dispatchers.IO) {
        val response = weatherService.getForecast(cityName, days).execute()
        if (response.isSuccessful) {
            val body = response.body()
            return@withContext UIModel.success(
                data = CityWeather(
                    cityName = body?.location?.name,
                    dateTime = LocalDateTime
                        .now()
                        ?.format(DateTimeFormatter.ofPattern("FormatStyle.LONG")),
                    iconUrl = body?.current?.condition?.icon,
                    temp = body?.current?.temp_c?.toString(),
                    conditionDesc = body?.current?.condition?.text,
                    forecast = body?.forecast?.forecastday?.map { weatherForecastDay ->
                        CityWeatherForecastItem(
                            dayOfWeek = LocalDateTime.parse(weatherForecastDay?.date, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))
                                ?.dayOfWeek
                                ?.getDisplayName(TextStyle.FULL, Locale.US),
                            icon = weatherForecastDay?.day?.condition?.icon,
                            tempDay = weatherForecastDay?.day?.maxtemp_c?.toString(),
                            tempNight = weatherForecastDay?.day?.mintemp_c?.toString(),
                        )
                    }
                )
            )
        }
        else {
            return@withContext UIModel.error(response.errorBody().toString())
        }
    }
}