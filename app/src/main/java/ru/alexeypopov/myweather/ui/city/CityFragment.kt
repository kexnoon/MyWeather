package ru.alexeypopov.myweather.ui.city

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import com.bumptech.glide.Glide
import ru.alexeypopov.myweather.App
import ru.alexeypopov.myweather.R
import ru.alexeypopov.myweather.common.UIModelStatus
import ru.alexeypopov.myweather.databinding.FragmentCityBinding

class CityFragment : Fragment() {

    private val viewModel: CityViewModel by viewModels()
    private lateinit var binding: FragmentCityBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCityBinding.inflate(inflater, container, false)
        initObservers()
        initViews()
        viewModel.checkCityWeather(App.DEFAULT_CITY)
        return binding.root
    }

    private fun initViews() {
        binding.layoutSwipe.setOnRefreshListener {
            viewModel.checkCityWeather(App.DEFAULT_CITY)
        }
    }

    private fun initObservers() {
        viewModel.cityWeatherLiveData.observe(viewLifecycleOwner) { cityWeather ->
            when(cityWeather.status) {
                UIModelStatus.ERROR -> {
                    binding.layoutSwipe.isRefreshing = false
                    Toast.makeText(context, cityWeather?.message, Toast.LENGTH_SHORT).show()
                }
                UIModelStatus.SUCCESS -> {
                    binding.layoutSwipe.isRefreshing = false
                    val weatherData = cityWeather?.data
                    binding.tvCity.text = weatherData?.cityName
                    binding.tvDate.text = weatherData?.dateTime
                    binding.tvCurrentTemp.text = weatherData?.temp
                    binding.tvCurrentConditions.text = weatherData?.conditionDesc

                    Glide.with(binding.ivCurrentWeather)
                        .load("https:${weatherData?.iconUrl}")
                        .centerInside()
                        .into(binding.ivCurrentWeather)
                }
                UIModelStatus.LOADING -> binding.layoutSwipe.isRefreshing = true
            }
        }
    }
}