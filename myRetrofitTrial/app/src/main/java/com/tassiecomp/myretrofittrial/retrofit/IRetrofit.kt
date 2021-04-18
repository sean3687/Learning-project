package com.tassiecomp.myretrofittrial.retrofit

import com.google.gson.JsonElement
import com.tassiecomp.myretrofittrial.utils.API.searchPhotos
import com.tassiecomp.myretrofittrial.utils.API.searchUsers
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface IRetrofit {

    @GET(searchPhotos)

    fun searchPhotos(@Query("query") searchTerm:String): Call<JsonElement>

    @GET(searchUsers)
    fun searchUsers(@Query("query") searchTerm: String): Call<JsonElement>
}