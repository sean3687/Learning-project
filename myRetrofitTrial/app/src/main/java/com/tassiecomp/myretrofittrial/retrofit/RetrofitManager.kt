package com.tassiecomp.myretrofittrial.retrofit

import com.tassiecomp.myretrofittrial.utils.API
import com.tassiecomp.myretrofittrial.utils.RESPONSE_STATE

class RetrofitManager {

    companion object {
        val instance = RetrofitManager()

    }

    private val iRetrofit: IRetrofit? =
        RetrofitClient.getClient(API.BASE_URL)?.create(IRetrofit::class.java)

    //사진검색 API호출
    fun searchPhotos(searchTerm:String?, completion:(RESPONSE_STATE) -> Unit){
        val term = searchTerm.let{
            it
        }?: ""
        val call = iRetrofit?.searchPhotos(searchTerm = term).let {
            it
        }?: return

        call.enqueue(object)
    }
}