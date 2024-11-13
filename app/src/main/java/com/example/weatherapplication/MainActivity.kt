package com.example.weatherapplication

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide

class MainActivity : AppCompatActivity() {
    private lateinit var weatherViewModel: WeatherViewModel
    private lateinit var weatherViewModelFactory: WeatherViewModelFactory
    private lateinit var repo: Repo
    private lateinit var loader : ProgressBar

    private lateinit var edtCity : EditText
    private lateinit var btnWeather : Button
    private lateinit var imgWeather : ImageView
    private lateinit var textLocation : TextView
    private lateinit var textWeather: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()

        btnWeather.setOnClickListener{
            weatherViewModel.getWeatherViewModel(edtCity.text.toString())
            weatherViewModel.weatherLiveData.observe(this){
                val weather = it.current.condition.text
                val tempInC = it.current.temp_c
                textWeather.text = "$weather, $tempInC"

                val image = "https:${it.current.condition.icon}"
                Glide.with(this).load(image).into(imgWeather)

                val city = it.location.name
                val state = it.location.region
                val country = it.location.country
                textLocation.text = "$city, $state, $country"
            }


        }




//        weatherViewModel.loaderLiveData.observe(this){
//            if(it){
//                loader.visibility = View.VISIBLE
//            }
//            else{
//                loader.visibility = View.GONE
//            }
//        }
    }

    private fun init(){
        repo = Repo(RetrofitBuilder.getInstance())
        weatherViewModelFactory = WeatherViewModelFactory(repo)
        weatherViewModel = ViewModelProvider(this,weatherViewModelFactory)[WeatherViewModel::class.java]
       // loader = findViewById(R.id.loader)
        edtCity = findViewById(R.id.edtCity)
        btnWeather = findViewById(R.id.btnWeather)
        imgWeather = findViewById(R.id.imgWeather)
        textLocation = findViewById(R.id.textLocation)
        textWeather = findViewById(R.id.textWeather)
    }
}