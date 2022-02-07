package com.example.weatherapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.weatherapplication.adapter.ForecastAdapter
import com.example.weatherapplication.databinding.ActivityForecastBinding
import com.example.weatherapplication.model.Forecast
import com.example.weatherapplication.model.ForecastTemp
import java.util.ArrayList

class ForecastActivity : AppCompatActivity() {
    private lateinit var binding: ActivityForecastBinding
    private var forecastList = ArrayList<Forecast>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForecastBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title = "Forecast"
        createForecast()
        initAdapter()
    }

    private fun createForecast(){
        forecastList.add(Forecast(1644026400,1644010809,1644048946, ForecastTemp(278.23f,273.78f,279.68f),1009f,41))
        forecastList.add(Forecast(1644112800,1644097159,1644135407, ForecastTemp(277.59f,272.38f,279.68f),1059f,45))
        forecastList.add(Forecast(1644199200,1644011809,1644049946, ForecastTemp(288.23f,265.78f,299.68f),1049f,46))
        forecastList.add(Forecast(1644285600,1644012809,1644050946, ForecastTemp(291.23f,283.78f,299.68f),1039f,49))
        forecastList.add(Forecast(1644372000,1644013809,1644051946, ForecastTemp(278.23f,270.78f,289.68f),1029f,39))
        forecastList.add(Forecast(1644458400,1644014809,1644052946, ForecastTemp(288.23f,274.78f,299.68f),1019f,50))
        forecastList.add(Forecast(1644544800,1644015809,1644053946, ForecastTemp(300.23f,293.78f,301.68f),1009f,65))
        forecastList.add(Forecast(1644631200,1644016809,1644054946, ForecastTemp(295.23f,291.78f,310.68f),1009f,55))
        forecastList.add(Forecast(1644717600,1644017809,1644055946, ForecastTemp(286.23f,287.78f,288.68f),1009f,54))
        forecastList.add(Forecast(1644804000,1644018809,1644056946, ForecastTemp(291.23f,288.78f,298.68f),1009f,44))
        forecastList.add(Forecast(1644887865,1644019809,1644057946, ForecastTemp(293.23f,289.78f,299.68f),1309f,47))
        forecastList.add(Forecast(1644974265,1644020809,1644058946, ForecastTemp(300.23f,290.78f,302.68f),1109f,48))
        forecastList.add(Forecast(1645060665,1644021809,1644059946, ForecastTemp(294.23f,292.78f,299.68f),1099f,49))
        forecastList.add(Forecast(1645147065,1644022809,1644060946, ForecastTemp(293.23f,292.78f,296.68f),1089f,43))
        forecastList.add(Forecast(1645233465,1644023809,1644061946, ForecastTemp(297.23f,293.78f,298.68f),1099f,42))
        forecastList.add(Forecast(1645319865,1644024809,1644062946, ForecastTemp(295.23f,294.78f,299.68f),1039f,41))
    }

    private fun initAdapter(){
        binding.recyclerView.adapter = ForecastAdapter(forecastList)
    }
}