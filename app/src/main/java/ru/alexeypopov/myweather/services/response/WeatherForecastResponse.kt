package ru.alexeypopov.myweather.services.response

data class WeatherForecastResponse(
    val location: WeatherForecastLocation?,
    val current: ForecastItem?,
    val forecast: WeatherForecast?
)

data class WeatherForecast(
    val forecastday: List<WeatherForecastDay?>?
)

data class WeatherForecastDay(
    val astro: Astro?,
    val date: String?,
    val date_epoch: Long?,
    val day: Day?,
    val hour: List<ForecastItem>?
)

data class Day(
    val avghumidity: Double?,
    val avgtemp_c: Double?,
    val avgtemp_f: Double?,
    val avgvis_km: Double?,
    val avgvis_miles: Double?,
    val condition: Condition?,
    val daily_chance_of_rain: Int?,
    val daily_chance_of_snow: Int?,
    val daily_will_it_rain: Int?,
    val daily_will_it_snow: Int?,
    val maxtemp_c: Double?,
    val maxtemp_f: Double?,
    val maxwind_kph: Double?,
    val maxwind_mph: Double?,
    val mintemp_c: Double?,
    val mintemp_f: Double?,
    val totalprecip_in: Double?,
    val totalprecip_mm: Double?,
    val uv: Double?
)

data class Astro(
    val moon_illumination: String?,
    val moon_phase: String?,
    val moonrise: String?,
    val moonset: String?,
    val sunrise: String?,
    val sunset: String?
)

data class WeatherForecastLocation(
    val name: String?,
    val region: String?,
    val country: String?,
    val lat: Double?,
    val lon: Double?,
    val tz_id: String?,
    val localtime_epoch: Long?,
    val localtime: String?
)

data class Condition(
    val text: String?,
    val icon: String?,
    val code: Int?
)

data class ForecastItem(
    val chance_of_rain: Int?,
    val chance_of_snow: Int?,
    val cloud: Int?,
    val condition: Condition?,
    val dewpoint_c: Double?,
    val dewpoint_f: Double?,
    val feelslike_c: Double?,
    val feelslike_f: Double?,
    val gust_kph: Double?,
    val gust_mph: Double?,
    val heatindex_c: Double?,
    val heatindex_f: Double?,
    val humidity: Int?,
    val is_day: Int?,
    val precip_in: Double?,
    val precip_mm: Double?,
    val pressure_in: Double?,
    val pressure_mb: Double?,
    val temp_c: Double?,
    val temp_f: Double?,
    val time: String?,
    val time_epoch: Long?,
    val uv: Double?,
    val vis_km: Double?,
    val vis_miles: Double?,
    val will_it_rain: Int?,
    val will_it_snow: Int?,
    val wind_degree: Int?,
    val wind_dir: String?,
    val wind_kph: Double?,
    val wind_mph: Double?,
    val windchill_c: Double?,
    val windchill_f: Double?,
    val last_updated: String?,
    val last_updated_epoch: Long?
)