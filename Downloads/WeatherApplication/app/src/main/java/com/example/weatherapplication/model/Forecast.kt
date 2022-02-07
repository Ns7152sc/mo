package com.example.weatherapplication.model

data class Forecast(val date: Long, val sunrise: Long, val sunset: Long, val temp: ForecastTemp, val pressure: Float, val humidity: Int)