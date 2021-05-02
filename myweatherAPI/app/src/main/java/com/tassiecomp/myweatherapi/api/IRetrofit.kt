package com.tassiecomp.myweatherapi.api

import com.google.gson.JsonElement
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface IRetrofit {
    @GET("/data/2.5/weather?")
    fun getCityData(@Query("q") searchTerm: String): Call<JsonElement>

    @GET("/data/2.5/weather?")
    fun getGridData(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("units") unit:String
    ): Call<JsonElement>


//    @GET("park/app/login.php")
//    fun loginUser(
//        @Query("username") username: String?,
//        @Query("password") passwoord: String?,
//        @Query("user_type") user_type: Int,
//        @Query("device_id") device_id: String?
//    ): Call<Result?>?

}