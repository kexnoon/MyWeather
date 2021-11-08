package ru.alexeypopov.myweather.common

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.alexeypopov.myweather.repository.WeatherRepository
import ru.alexeypopov.myweather.services.WeatherService

/**
 * Деревенский DI, поменяю когда сяду разбирать Hilt
 */
object DiContainer {
    private val retrofitWeather by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.weatherapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(
                OkHttpClient.Builder()
                    .addInterceptor { chain ->
                        chain.proceed(
                            chain.request().newBuilder().url(
                                chain.request().url().newBuilder()
                                    .addQueryParameter(
                                        "key",
                                        "4c78434a3e5942eab0a221039210611"
                                    ).build()
                            ).build()
                        )
                    }
                    .build()
            )
            .build()
    }

    val weatherService by lazy { retrofitWeather.create(WeatherService::class.java) }

    val weatherRepository by lazy { WeatherRepository(weatherService) }
}