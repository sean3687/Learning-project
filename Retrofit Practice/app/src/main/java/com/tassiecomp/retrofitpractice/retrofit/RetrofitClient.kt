package com.tassiecomp.retrofitpractice.retrofit

import android.content.ContentValues.TAG
import android.util.Log
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//싱글턴-메모리 하나만사용
object RetrofitClient {
    //레트로핏 클라이언트 선언
    private lateinit var retrofitClient: Retrofit
    //레트로핏 클라이어느 가져오기
    fun getClient(baseUrl: String): Retrofit? { //매개 변수로 base url이 들어오고
        Log.d(TAG, "RetrofitClient - getClient() called")

        if(retrofitClient == null){

            //레트로핏 빌더를 통해 인스턴스 생성
            retrofitClient = Retrofit.Builder()
                .baseUrl(baseUrl) //매개변수로 들어온 url이 여기로 들어온다.
                .addConverterFactory(GsonConverterFactory.create())

                    //
                .build()
        }
        return retrofitClient
    }

}