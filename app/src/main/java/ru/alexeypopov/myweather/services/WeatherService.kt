package ru.alexeypopov.myweather.services

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import ru.alexeypopov.myweather.services.response.WeatherForecastResponse

interface WeatherService {
    @GET("v1/forecast.json")
    fun getForecast(
        @Query("q") cityName: String?,
        @Query("days") days: Int?
    ):Call<WeatherForecastResponse>
}