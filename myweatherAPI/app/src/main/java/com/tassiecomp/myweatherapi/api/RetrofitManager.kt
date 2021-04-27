package com.tassiecomp.myweatherapi.api

import android.util.Log
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.gson.JsonElement
import com.tassiecomp.myweatherapi.App
import com.tassiecomp.myweatherapi.utils.API
import com.tassiecomp.myweatherapi.utils.RESPONSE_STATE
import retrofit2.Call
import retrofit2.Response

class RetrofitManager {

    companion object {
        val instance = RetrofitManager()

    }


    private val iRetrofit: IRetrofit? =
        RetrofitClient.getClient(API.BASE_URL)?.create(IRetrofit::class.java)

    fun getData_byCity(searchTerm: String?, completion: (RESPONSE_STATE, String) -> Unit) {
        val term = searchTerm.let {
            it
        } ?: ""

        val call = iRetrofit?.getCityData(searchTerm = term).let {
            it
        } ?: return

        call.enqueue(object : retrofit2.Callback<JsonElement> {
            override fun onResponse(call: Call<JsonElement>, response: Response<JsonElement>) {
                Log.d(
                    "TAG",
                    "RetrofitManager - onResponse() called() / response: ${response.body()}"
                )

                when (response.code()) {
                    200 -> {
                        completion(RESPONSE_STATE.OKAY, response.body().toString())
                    }
                }

            }


            override fun onFailure(call: Call<JsonElement>, t: Throwable) {
                Log.d("TAG", "RetrofitManager - onFailure() called() / t: $t")
                //이제 호출된 결과값이 들어온다.
                completion(RESPONSE_STATE.FAIL, t.toString())
            }


        })
    }

    fun getData_byGrid(
        latitude: Double?,
        longitude: Double?,
        completion: (RESPONSE_STATE, String) -> Unit
    ) {
        val lat = latitude.let {
            it
        } ?: ""

        val lon = longitude.let {
            it
        } ?: ""

        val call = iRetrofit?.getGridData(latitude = lat as Double, longitude = lon as Double).let {
            it
        } ?: return

        call.enqueue(object : retrofit2.Callback<JsonElement> {
            override fun onResponse(call: Call<JsonElement>, response: Response<JsonElement>) {
                Log.d(
                    "TAG",
                    "RetrofitManager - onResponse() called() / response: ${response.body()}"
                )

                when (response.code()) {
                    200 -> {
                        completion(RESPONSE_STATE.OKAY, response.body().toString())
                    }
                }

            }


            override fun onFailure(call: Call<JsonElement>, t: Throwable) {
                Log.d("TAG", "RetrofitManager - onFailure() called() / t: $t")
                //이제 호출된 결과값이 들어온다.
                completion(RESPONSE_STATE.FAIL, t.toString())
            }


        })
    }




}