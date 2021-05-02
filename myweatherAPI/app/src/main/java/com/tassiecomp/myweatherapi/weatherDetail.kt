package com.tassiecomp.myweatherapi

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.Target
import com.tassiecomp.myweatherapi.Model.Weather
import com.tassiecomp.myweatherapi.utils.capitalize_first_word
import kotlinx.android.synthetic.main.activity_weather_detail.*

class weatherDetail : AppCompatActivity() {

    private var weatherList = ArrayList<Weather>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather_detail)

        //bring Array Data
        val bundle = intent.getBundleExtra("array_bundle")
        weatherList = bundle?.getSerializable("weather_array_list") as ArrayList<Weather>


        //assign value for setText
        val mainTemp = weatherList[0].main_temp
        val mainFeelTemp = weatherList[0].main_feelslike
        val mainMinTemp = weatherList[0].main_mintemp
        val mainMaxTemp = weatherList[0].main_maxtemp
        val mainHumidity = weatherList[0].main_humidity
        val windSpeed = weatherList[0].wind_speed
        val windDeg = weatherList[0].wind_deg
        val weatherDescription = weatherList[0].weather_description
        val weatherIcon = weatherList[0].weather_icon
        val nameCity = weatherList[0].name_city

        Log.d(
            "weatherDetaildd",
            "nameCity: $mainTemp ,mainmintemp: $mainMinTemp weatherIcon:$weatherIcon"
        )

        //setText
        city_name.text = nameCity
        main_temp.text = "${mainTemp.toString()}°"
        minmax_temp.text = "$mainMinTemp°/$mainMaxTemp°"


        Log.d(
            "weatherDetaildd",
            "nameCity: $mainTemp ,mainmintemp: $mainMinTemp weatherIcon:${weatherDescription?.let { weatherDescription.capitalize_first_word(it) }}"
        )

        //setImage(ICON)

        val iconURL:String = "http://openweathermap.org/img/wn/$weatherIcon@2x.png"

        Glide.with(App.instance)
            .load("$iconURL")// image url
            .placeholder(R.drawable.ic_launcher_background) // any placeholder to load at start
            .error(R.drawable.ic_launcher_foreground)  // any image in case of error
            .into(weather_icon);  // imageview object


    }
}