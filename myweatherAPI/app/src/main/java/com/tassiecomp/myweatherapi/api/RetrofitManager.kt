package com.tassiecomp.myweatherapi.api

import android.util.Log
import com.google.gson.JsonElement
import com.tassiecomp.myweatherapi.utils.API
import com.tassiecomp.myweatherapi.utils.RESPONSE_STATE
import retrofit2.Call
import retrofit2.Response

class RetrofitManager {

    companion object {
        val instance = RetrofitManager()
    }
    private val iRetrofit: IRetrofit? =
        RetrofitClient.getClient(API.BASE_URL)?.create(IRetrofit::class.java)

    fun getData(searchTerm: String?, completion: (RESPONSE_STATE, String) -> Unit) {
        val term = searchTerm.let {
            it
        }?: ""

        val call = iRetrofit?.getData(searchTerm = term).let {
            it
        }?: return

        call.enqueue(object :retrofit2.Callback<JsonElement> {
            override fun onResponse(call: Call<JsonElement>, response: Response<JsonElement>) {
                Log.d("TAG","RetrofitManager - onResponse() called() / response: ${response.body()}")
                completion(RESPONSE_STATE.OKAY, response.body().toString())
            }

            override fun onFailure(call: Call<JsonElement>, t: Throwable) {
                Log.d("TAG","RetrofitManager - onFailure() called() / t: $t")
                //이제 호출된 결과값이 들어온다.
                completion(RESPONSE_STATE.FAIL, t.toString())
            }


        })
    }
}