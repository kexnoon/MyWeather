package ru.alexeypopov.myweather.ui.city

import androidx.lifecycle.*
import kotlinx.coroutines.launch
import ru.alexeypopov.myweather.App
import ru.alexeypopov.myweather.common.DiContainer.weatherRepository
import ru.alexeypopov.myweather.common.UIModelLiveData
import ru.alexeypopov.myweather.common.UIModelStatus
import ru.alexeypopov.myweather.ui.models.CityWeather

class CityViewModel(): ViewModel() {

    var cityWeatherLiveData = UIModelLiveData<CityWeather?>()

    fun checkCityWeather(name: String) {
        viewModelScope.launch {
            cityWeatherLiveData.postLoading()
            val getWeatherForecast = weatherRepository.getWeatherForecast(
                name,
                App.DEFAULT_FORECAST_DAYS
            )
            when (getWeatherForecast.status) {
                UIModelStatus.SUCCESS -> cityWeatherLiveData.postSuccess(getWeatherForecast.data)
                UIModelStatus.ERROR -> cityWeatherLiveData.postError(getWeatherForecast.message)
                else -> cityWeatherLiveData.postLoading()
            }
        }
    }
}