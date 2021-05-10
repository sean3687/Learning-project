package com.tassiecomp.myweatherapi

import android.Manifest
import android.animation.Animator
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.tassiecomp.myweatherapi.App
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.gms.location.LocationServices
import com.tassiecomp.myweatherapi.Model.DailyWeather
import com.tassiecomp.myweatherapi.Model.Weather
import com.tassiecomp.myweatherapi.RecyclerView.DailyRecyclerViewAdapter
import com.tassiecomp.myweatherapi.api.RetrofitManager
import com.tassiecomp.myweatherapi.utils.*
import com.tassiecomp.myweatherapi.utils.API.exclude
import com.tassiecomp.myweatherapi.utils.API.unit
import com.tassiecomp.myweatherapi.utils.LocationManager.Companion.fusedLocationProviderClient
import kotlinx.android.synthetic.main.activity_weather_detail.*
import okhttp3.internal.addHeaderLenient
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import java.util.Date as Date


class MainActivity : AppCompatActivity() {


    private var weatherList = ArrayList<Weather>()
    private var dailyWeatherList = ArrayList<DailyWeather>()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather_detail)


        //Location Manager
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
        checkPermission()

        hamburger_menu.setOnClickListener {
        }


        //loadButton
        search_button.setOnClickListener {
            Log.d("TAG", "MainActivity - 검색버튼이 클릭되었다.")

            RetrofitManager.instance.getData_byCity(
                searchTerm = search_editText.text.toString(),
                completion = { responseState, responseDataArrayList_City ->

                    val userSearchInput = search_editText.text.toString()

                    when (responseState) {
                        RESPONSE_STATE.OKAY -> {
                            Log.d("TAG", "API 호출성공: $responseDataArrayList_City")
                            weatherList = responseDataArrayList_City!!
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

                            //setText
                            city_name.text = nameCity
                            main_temp.text = "${mainTemp.toString()}°"
                            minmax_temp.text = "$mainMinTemp°/$mainMaxTemp°"
                            description.text = weatherDescription?.let {
                                weatherDescription.capitalize_first_word(it)
                            }
                            sub_humidity_value.text = "$mainHumidity%"
                            sub_wind_value.text = "${windSpeed}m/s"
                            sub_feels_value.text = "$mainFeelTemp°"

                            //setImage(ICON)
                            Log.d("weatherIcon", "weather Icon:$weatherIcon")

                            val iconURL: String =
                                "https://openweathermap.org/img/wn/$weatherIcon@2x.png"

                            Glide.with(App.instance)
                                .load(iconURL)// image url
                                .placeholder(R.drawable.ic_baseline_cloud_queue_24) // any placeholder to load at start
                                .error(R.drawable.ic_baseline_error_outline_24)  // any image in case of error
                                .into(weather_icon);  // imageview object


                        }
                        RESPONSE_STATE.FAIL -> {
                            Log.d("TAG", "API 호출 에러 : $responseDataArrayList_City")
                            Toast.makeText(this, "City not Found", Toast.LENGTH_SHORT).show()
                        }
                    }
                })


        }

        refresh_weather_button.setOnClickListener {
            checkPermission()
            refresh_weather_button.speed = 2f
            refresh_weather_button.scale = 2f
            refresh_weather_button.playAnimation()
        }

        refresh_weather_button.addAnimatorListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator?) {
                Log.d("animation", "pass")
            }

            override fun onAnimationEnd(animation: Animator?) {
                val currentTime = getCurrentTime()
                Log.d("animation", "$currentTime")
                lastupdate.text = "Last update ${currentTime}"
            }

            override fun onAnimationCancel(animation: Animator?) {
                Log.d("animation", "pass")
            }

            override fun onAnimationRepeat(animation: Animator?) {
                Log.d("animation", "pass")
            }

        })

        //recycler instance
        App.dailyRecyclerViewAdapter = DailyRecyclerViewAdapter()
        App.dailyRecyclerViewAdapter.submitList(dailyWeatherList)

        //photo recycler view에 layoutmanager에서 layout manager type을 연결해준다.
        daily_recyclerview.layoutManager = LinearLayoutManager(this,RecyclerView.VERTICAL,false)

        daily_recyclerview.adapter = App.dailyRecyclerViewAdapter
    }

    private fun checkPermission() {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION),
                1
            )
        } else {
            LocationManager.instance.getLocations(
                completion = { responseState, responseGrid ->
                    unit
                    val lat = responseGrid!![0].latitude
                    val lon = responseGrid!![0].longitude
                    //LocationManager
                    when (responseState) {
                        //when LocationManager succeed
                        RESPONSE_STATE.OKAY -> {
                            RetrofitManager.instance.getData_byGrid(
                                latitude = lat,
                                longitude = lon,
                                units = unit,
                                completion = { responseState, responseDataArrayList_Grid ->
                                    //retrofitManager
                                    when (responseState) {
                                        RESPONSE_STATE.OKAY -> {
                                            //when RetrofitManager succeed
                                            Log.d(
                                                "TAG",
                                                "API Successful: $responseDataArrayList_Grid"
                                            )

                                            weatherList = responseDataArrayList_Grid!!

                                            //assign value for setText
                                            val mainTemp = weatherList[0].main_temp
                                            val mainFeelTemp = weatherList[0].main_feelslike
                                            val mainMinTemp = weatherList[0].main_mintemp
                                            val mainMaxTemp = weatherList[0].main_maxtemp
                                            val mainHumidity = weatherList[0].main_humidity
                                            val windSpeed = weatherList[0].wind_speed
                                            val windDeg = weatherList[0].wind_deg
                                            val weatherDescription =
                                                weatherList[0].weather_description
                                            val weatherIcon = weatherList[0].weather_icon
                                            val nameCity = weatherList[0].name_city

                                            //setText
                                            city_name.text = nameCity
                                            main_temp.text = "${mainTemp.toString()}°"
                                            minmax_temp.text = "$mainMinTemp°/$mainMaxTemp°"
                                            description.text = weatherDescription?.let {
                                                weatherDescription.capitalize_first_word(it)
                                            }
                                            sub_humidity_value.text = "$mainHumidity%"
                                            sub_wind_value.text = "${windSpeed}m/s"
                                            sub_feels_value.text = "$mainFeelTemp°"


                                            //setImage(ICON)

                                            val iconURL: String =
                                                "https://openweathermap.org/img/wn/$weatherIcon@2x.png"

                                            Log.d("weatherIcon", "weather Icon:$weatherIcon")
                                            Glide.with(this)
                                                .load(iconURL)
                                                .placeholder(R.drawable.ic_baseline_cloud_queue_24) // any placeholder to load at start
                                                .error(R.drawable.ic_baseline_cloud_queue_24)  // any image in case of error
                                                .into(weather_icon);  // imageview object

                                            //RetrofitManager Daily
                                            RetrofitManager.instance.getData_byDaily(
                                                latitude = lat,
                                                longitude = lon,
                                                units = unit,
                                                exclude = exclude,
                                                completion = { responseState, responseDataArrayList_Daily ->
                                                    when (responseState) {
                                                        RESPONSE_STATE.OKAY -> {
                                                            Log.d(
                                                                "TAG",
                                                                "API Successful_Daily: $responseDataArrayList_Daily"
                                                            )

                                                            dailyWeatherList = responseDataArrayList_Daily!!

                                                        }
                                                        RESPONSE_STATE.FAIL -> {
                                                            Log.d(
                                                                "TAG",
                                                                "API Load Error_Daily : $responseDataArrayList_Grid"
                                                            )
                                                            Toast.makeText(this, "Daily Load Failed", Toast.LENGTH_SHORT).show()
                                                        }
                                                    }
                                                }
                                            )

                                        }
                                        RESPONSE_STATE.FAIL -> {
                                            Log.d(
                                                "TAG",
                                                "API Load Error : $responseDataArrayList_Grid"

                                            )
                                            Toast.makeText(this, "current load Failed", Toast.LENGTH_SHORT).show()
                                        }
                                    }
                                })
                        }
                        RESPONSE_STATE.FAIL -> {
                            Toast.makeText(this, "Location load Failed", Toast.LENGTH_SHORT).show()
                        }
                    }

                }


            )
        }

    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == 1) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                if (ContextCompat.checkSelfPermission(
                        this,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    ) == PackageManager.PERMISSION_GRANTED
                ) {
                    Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show()

                } else {
                    Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    fun getCurrentTime(): String {
        val sdf = SimpleDateFormat("MM/dd a hh:mm")
        val currentDate = sdf.format(Date())
        return currentDate


    }


}


