package com.tassiecomp.myweatherapi

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.tassiecomp.myweatherapi.Model.Weather
import com.tassiecomp.myweatherapi.api.RetrofitManager
import com.tassiecomp.myweatherapi.utils.RESPONSE_STATE
import com.tassiecomp.myweatherapi.utils.getDouble
import com.tassiecomp.myweatherapi.utils.putDouble
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)

        load_button.setOnClickListener {
            Log.d("TAG", "MainActivity - 검색버튼이 클릭되었다.")

            RetrofitManager.instance.getData_byCity(
                searchTerm = editText_term.text.toString(),
                completion = { responseState, responseBody ->

                    val userSearchInput = editText_term.text.toString()

                    when (responseState) {
                        RESPONSE_STATE.OKAY -> {
                            Log.d("TAG", "API 호출성공: $responseBody")


                        }
                        RESPONSE_STATE.FAIL -> {
                            Log.d("TAG", "API 호출 에러 : $responseBody")
                        }
                    }
                })


        }

        get_current_weather.setOnClickListener {

            checkPermission()
            var sharedPreference = getSharedPreferences("gridLocation", Context.MODE_PRIVATE)
            val lat = sharedPreference.getDouble("lat", 0.0000)
            val lon = sharedPreference.getDouble("lon", 0.0000)
            Log.d("Location", "shared LAT&LON :$lat, $lon")


            RetrofitManager.instance.getData_byGrid(
                latitude = lat,
                longitude = lon,
                completion = { responseState, responseDataArrayList->


                when (responseState) {
                    RESPONSE_STATE.OKAY -> {
                        Log.d("TAG", "API 호출성공: $responseDataArrayList")

                        val intent = Intent(this, weatherDetail::class.java)

                        val bundle = Bundle()

                        bundle.putSerializable("weather_array_list", responseDataArrayList)

                        intent.putExtra("array_bundle", bundle)

                        startActivity(intent)
                    }
                    RESPONSE_STATE.FAIL -> {
                        Log.d("TAG", "API 호출 에러 : $responseDataArrayList")
                    }
                }
            })
            //open new activity
//            val weatherDetail_intent = Intent(this@MainActivity, weatherDetail::class.java)
//            startActivity(weatherDetail_intent)

        }
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
            getLocations()
        }

    }

    @SuppressLint("MissingPermission")
    private fun getLocations() {

        fusedLocationProviderClient.lastLocation.addOnSuccessListener {
            if (it == null) {
                Toast.makeText(this, "Error: can't load user location", Toast.LENGTH_SHORT)
                    .show()
            } else it.apply {
                val latitude = it.latitude
                val longitude = it.longitude
                Log.d("Location", "RAW LAT&LON :$latitude, $longitude")

                //sharedpreference
                var sharedPreference =
                    getSharedPreferences("gridLocation", Context.MODE_PRIVATE)
                val editor: SharedPreferences.Editor = sharedPreference.edit()
                editor.putDouble("lat", latitude)
                editor.putDouble("lon", longitude)
                editor.apply()

            }
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
                    getLocations()
                } else {
                    Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }


}


