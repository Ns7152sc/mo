package com.example.weatherapplication.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.weatherapplication.model.CurrentConditions
import com.example.weatherapplication.network.DataUseCase
import com.example.weatherapplication.network.ResultData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val dataUseCase: DataUseCase) : ViewModel() {

    fun getCurrentConditions(
        zip: String,
        units: String,
        appId: String
    ): LiveData<ResultData<CurrentConditions>> {

        return flow {
            emit(ResultData.Loading())
            try {
                emit(dataUseCase.getCurrentConditions(zip, units, appId))
            } catch (e: Exception) {
                e.printStackTrace()
                emit(ResultData.Exception())
            }
        }.asLiveData(Dispatchers.IO)
    }

    fun getCurrentConditions(
        lat: String,
        lang: String,
        units: String,
        appId: String
    ): LiveData<ResultData<CurrentConditions>> {

        return flow {
            emit(ResultData.Loading())
            try {
                emit(dataUseCase.getCurrentConditions(lat, lang, units, appId))
            } catch (e: Exception) {
                e.printStackTrace()
                emit(ResultData.Exception())
            }
        }.asLiveData(Dispatchers.IO)
    }

}