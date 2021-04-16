package com.tassiecomp.airPolution.retrofit

import android.util.Log
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    //레트로핏 클라이언트 선언
    private var retrofitClient: Retrofit? = null

    //레트로핏 클라이언트 가져오기
    fun getClient(baseUrl: String):Retrofit?{
        Log.d("TAG","RetrofitClient - getClient()called")

        if (retrofitClient ==null) { //레트로핏 클라이언트 유무 확인

            retrofitClient = Retrofit.Builder()
                .baseUrl(baseUrl) //위에 getclient에 변수를 가져온다.
                .addConverterFactory(GsonConverterFactory.create()) //Gson converter추가
                .build()

        }
        return retrofitClient
    }
}