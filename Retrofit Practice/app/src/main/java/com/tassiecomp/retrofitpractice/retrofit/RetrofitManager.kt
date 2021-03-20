package com.tassiecomp.retrofitpractice.retrofit

import android.util.Log
import com.google.gson.JsonElement
import com.tassiecomp.retrofitpractice.retrofit.IRetrofit
import com.tassiecomp.retrofitpractice.utils.API
import com.tassiecomp.retrofitpractice.utils.Constants.TAG
import com.tassiecomp.retrofitpractice.utils.RESPONSE_STATE
import retrofit2.Call
import retrofit2.Response


class RetrofitManager {

    companion object {
        val instance = RetrofitManager()
    }

    //http콜 만들기
    //레트로핏 인터페이스 가져오기
    private val iRetrofit: IRetrofit? =
        RetrofitClient.getClient(API.BASE_URL)?.create(IRetrofit::class.java)

    //사진검색 api호출
    fun searchPhotos(searchTerm: String?, completion: (RESPONSE_STATE,String) -> Unit) {

        val term = searchTerm.let {
            it
        } ?: ""

        // val term = searchTerm?: ""

        val call = iRetrofit?.searchPhotos(searchTerm = term).let {
            it
        } ?: return // interface에 search photos를 실행한다.

        call.enqueue(object: retrofit2.Callback<JsonElement>{

            //응답 성공시
            override fun onResponse(call: Call<JsonElement>, response: Response<JsonElement>) {
                Log.d(TAG, "RetrofitManager - onResponse() called / t:${response.raw()}")

                completion(RESPONSE_STATE.OKAY,response.raw().toString())
            }
            //응답 실패시
            override fun onFailure(call: Call<JsonElement>, t: Throwable) {
                Log.d(TAG,"RetrofitManager = onFailure() called / t:$t")

                completion(RESPONSE_STATE.FAIL,t.toString())
            }

        })


    }

}