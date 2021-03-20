package com.tassiecomp.retrofitpractice.retrofit

import com.google.gson.JsonElement
import com.tassiecomp.retrofitpractice.utils.API
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface IRetrofit {
//retrofit interface
    //https://www.unsplash.com/search/search/photos/?query=""
    @GET(API.SEARCH_PHOTOS)
    fun searchPhotos(@Query("query") searchTerm: String) : Call<JsonElement>

    @GET(API.SEARCH_USERS)

    fun searchUsers(@Query("query") searchTerm: String) : Call<JsonElement>
}