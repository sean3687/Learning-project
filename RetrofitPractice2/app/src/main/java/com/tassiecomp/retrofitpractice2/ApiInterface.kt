package com.tassiecomp.retrofitpractice2

import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {


    // we bring end points here https://jsonplaceholder.typicode.com/post <- this is the end point
    @GET("/posts")
    fun getData(): Call<List<myDataItem>> // and we get response from myDataItem -> this call myDataItem


}