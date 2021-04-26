package com.tassiecomp.myweatherapi

import android.annotation.SuppressLint
import android.content.Intent
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.tassiecomp.myweatherapi.api.RetrofitManager
import com.tassiecomp.myweatherapi.utils.RESPONSE_STATE
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var fusedLocationClient: FusedLocationProviderClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        load_button.setOnClickListener {
            Log.d("TAG", "MainActivity - 검색버튼이 클릭되었다.")

            RetrofitManager.instance.getData_byCity(
                searchTerm = editText_term.text.toString(),
                completion = { responseState, responseBody ->

                    val userSearchInput = editText_term.text.toString()

                    when (responseState) {
                        RESPONSE_STATE.OKAY -> {
                            Log.d("TAG", "API 호출성공: $responseBody")

                            val intent = Intent(this, weatherDetail::class.java)

                            val bundle = Bundle()


                        }
                        RESPONSE_STATE.FAIL -> {
                            Log.d("TAG", "API 호출 에러 : $responseBody")
                        }
                    }
                })



        }

        get_current_weather.setOnClickListener{
            val lat = obtieneLocalizacion()
            Log.d("LocationData","dddddddddddddddddddddddddddddddddfunction value ${lat}")

            RetrofitManager.instance.getData_byGrid(
                latitude = 37.384545,
                longitude = 127.123819,
                completion = { responseState, responseBody ->


                    when (responseState) {
                        RESPONSE_STATE.OKAY -> {
                            Log.d("TAG", "API 호출성공: $responseBody")

                            val intent = Intent(this, weatherDetail::class.java)

                            val bundle = Bundle()


                        }
                        RESPONSE_STATE.FAIL -> {
                            Log.d("TAG", "API 호출 에러 : $responseBody")
                        }
                    }
                })

        }


    }

    @SuppressLint("MissingPermission")
    private fun obtieneLocalizacion() {
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location: Location? ->
                if (location != null){
                    location.latitude
                    location.longitude
                    Log.d("LocationData","ddddddddddddddddsuccess")
                }else{
                    Toast.makeText(this,"Error: Location Permission needed", Toast.LENGTH_SHORT).show()
                    Log.d("LocationData","dddddddddddddddd failed")
                }

            }

    }


}