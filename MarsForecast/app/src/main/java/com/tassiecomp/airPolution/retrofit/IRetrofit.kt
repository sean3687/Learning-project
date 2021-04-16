package com.tassiecomp.airPolution.retrofit

import com.google.gson.JsonElement
import com.tassiecomp.airPolution.utils.API
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface IRetrofit {

    @GET(API.SEARCH_PHOTOS)

    fun searchPhotos(@Query("query") searchTerm:String): Call<JsonElement> //여기에 parameter을 넣어준다.
    //위에 뜻은

    @GET(API.SEARCH_USERS)
    fun searchUsers(@Query("query") searchTerm: String): Call<JsonElement>
}