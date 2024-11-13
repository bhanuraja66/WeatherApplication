package com.example.weatherapplication

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapplication.datamodel.WeatherResponseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class WeatherViewModel @Inject constructor(private val repo: Repo): ViewModel() {

    val weatherLiveData = MutableLiveData<WeatherResponseModel>()
    val loaderLiveData = MutableLiveData(false)

    fun getWeatherViewModel(city : String) {
        viewModelScope.launch(Dispatchers.IO) {
            //network request is sent, make loader visible
            loaderLiveData.postValue(true)
            val response = repo.getWeatherRepo(city)
            if (response.isSuccessful){
                weatherLiveData.postValue(response.body())
                //we have response from server, make loader gone
               loaderLiveData.postValue(false)
            }
        }

    }
}