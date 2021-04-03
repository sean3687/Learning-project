package com.tassiecomp.instagram

import android.app.Application
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.gson.Gson
import com.squareup.okhttp.Interceptor
import com.squareup.okhttp.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class MasterApplication: Application() {

    lateinit var service: Retrofit

    override fun onCreate() {
        super.onCreate()


    }

    fun createRetrofit(){
        val header = okhttp3.Interceptor{
            //서버에서 나가는 부분을 request를 잡는다.
            val original = it.request()
            //그 request를 auth 로 개조시킨다.
            val request = original.newBuilder()
                .header("Authorization","")
                .build()
            it.proceed(request)
        }

        val client = okhttp3.OkHttpClient.Builder()
            .addInterceptor(header)
            .addNetworkInterceptor(StethoInterceptor())
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("http://mellowcode.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        service = retrofit.create(RetrofitService::class.java)
    }
}