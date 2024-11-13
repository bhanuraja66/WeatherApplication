package com.example.weatherapplication

import com.example.weatherapplication.datamodel.WeatherResponseModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitServices {

    @GET("/v1/current.json?key=0c70bb8fa1204585b69182643221706")
    suspend fun getWeatherDetail(@Query("q") city : String) : Response<WeatherResponseModel>
}