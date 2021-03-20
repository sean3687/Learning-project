package com.tassiecomp.randomquotegenerator

import com.tassiecomp.randomquotegenerator.api.CatJson
import retrofit2.Call
import retrofit2.http.GET

interface ApiRequests {

    //여기에 API기능들을 추가하는것같다.
    @GET("/facts/random")
    fun getCatFact(): Call<CatJson>

}