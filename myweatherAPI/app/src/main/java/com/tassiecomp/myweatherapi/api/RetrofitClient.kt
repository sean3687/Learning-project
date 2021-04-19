package com.tassiecomp.myweatherapi.api

import android.util.Log
import com.tassiecomp.myweatherapi.utils.API
import com.tassiecomp.myweatherapi.utils.API.BASE_URL
import com.tassiecomp.myweatherapi.utils.isJsonArray
import com.tassiecomp.myweatherapi.utils.isJsonObject
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import org.json.JSONObject
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private var retrofitClient: Retrofit? = null

    fun getClient(baseUrl: String):Retrofit? {
        Log.d("TAG","RetrofitClient - getClient()called")

        val client = OkHttpClient.Builder()

        val loggingInterceptor = HttpLoggingInterceptor(object: HttpLoggingInterceptor.Logger {
            override fun log(message: String) {
                when {
                    message.isJsonObject() ->
                        Log.d("TAG", JSONObject(message).toString(4))

                    message.isJsonArray() ->
                        Log.d("TAG", JSONObject(message).toString(4))
                    else -> {
                        try {
                            Log.d("TAG", JSONObject(message).toString(4))
                        } catch (e: Exception) {
                            Log.d("TAG", message)
                        }
                    }
                }
            }

        })

        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        client.addInterceptor(loggingInterceptor)

        val baseParameterInterceptor: Interceptor = (object: Interceptor {
            override fun intercept(chain: Interceptor.Chain): Response {
                Log.d("TAG", "RetrofitClient - intercept() called")

                val originalRequest = chain.request()

                val addUrl = originalRequest.url.newBuilder().addQueryParameter("client_id", API.CLIENT_ID).build()

                val finalRequest = originalRequest.newBuilder()
                    .url(addUrl)
                    .method(originalRequest.method, originalRequest.body)
                    .build()
                return chain.proceed(finalRequest)
            }


        })

        client.addInterceptor(baseParameterInterceptor)


        if (retrofitClient ==null) {
            retrofitClient = Retrofit.Builder()
                .baseUrl(BASE_URL) //위에 getclient에 변수를 가져온다.
                .addConverterFactory(GsonConverterFactory.create()) //Gson converter추가
                .client(client.build())
                .build()

        }
        return retrofitClient
    }

}