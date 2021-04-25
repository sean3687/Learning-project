package com.tassiecomp.myweatherapi.api

import com.google.gson.JsonElement
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface IRetrofit {
    @GET("/data/2.5/weather?")
    fun getCityData(@Query("q") searchTerm:String): Call<JsonElement>
}