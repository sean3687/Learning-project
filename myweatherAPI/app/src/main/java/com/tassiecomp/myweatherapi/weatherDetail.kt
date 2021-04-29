package com.tassiecomp.myweatherapi

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.tassiecomp.myweatherapi.Model.Weather
import kotlinx.android.synthetic.main.activity_weather_detail.*

class weatherDetail : AppCompatActivity() {

    private var weatherList = ArrayList<Weather>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather_detail)

        //bring Array Data
        val bundle = intent.getBundleExtra("array_bundle")
        weatherList = bundle?.getSerializable("weather_array_list") as ArrayList<Weather>

//        val check = weatherList[2]
        Log.d("weatherDetail", "$weatherList")
        //setText
//        main_temp.text = weatherList[0].toString()
//        feelslike_temp.text = weatherList[1].toString()
//        min_temp.text = weatherList[2].toString()
//        max_temp.text = weatherList[3].toString()
//        humidity.text = weatherList[4].toString()
//        windspeed.text = weatherList[5].toString()
////        winddeg.text = weatherList[0].toString()
//        description.text = weatherList[7].toString()
//        icon


    }
}