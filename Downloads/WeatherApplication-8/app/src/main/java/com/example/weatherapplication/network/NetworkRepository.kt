package com.example.weatherapplication.network

import com.example.weatherapplication.model.CurrentConditions
import com.example.weatherapplication.model.Forecast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class NetworkRepository @Inject constructor(private var apiService: Api) {


    suspend fun getForeCast(
        zip: String,
        units: String,
        appId: String
    ): Forecast {
        return apiService.getForecast(zip, units, appId)
    }

    suspend fun getForeCast(
        lat: String,
        lang: String,
        units: String,
        appId: String
    ): Forecast {
        return apiService.getForecast(lat, lang, units, appId)
    }

    suspend fun getCurrentConditions(
        zip: String,
        units: String,
        appId: String
    ): CurrentConditions {
        return apiService.getCurrentConditions(zip, units, appId)
    }

    suspend fun getCurrentConditions(
        lat: String,
        lang: String,
        units: String,
        appId: String
    ): CurrentConditions {
        return apiService.getCurrentConditions(lat, lang, units, appId)
    }

}