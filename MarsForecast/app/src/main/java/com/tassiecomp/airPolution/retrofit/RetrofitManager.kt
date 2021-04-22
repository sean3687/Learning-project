package com.tassiecomp.airPolution.retrofit

import android.util.Log
import com.google.gson.JsonElement
import com.tassiecomp.airPolution.model.Photo
import com.tassiecomp.airPolution.utils.API
import com.tassiecomp.airPolution.utils.RESPONSE_STATE
import retrofit2.Call
import retrofit2.Response
import java.text.SimpleDateFormat


class RetrofitManager {

    //싱글톤이 적용되도록 retrofit manager을 companion object에만든다.
    companion object {
        val instance = RetrofitManager()

    }

    //http콜 만들기
    //아까 만들었던 retofit client를 get client로 가져온다.
    //그리고 getClient()안에 parameter가 base URL이었으니까 constant로 만들었던 API.BASE_URL로가져온다.
    //이렇게 create까지하면 레트로핏 인터페이스까지 가져오게된다.
    private val iRetrofit: IRetrofit? =
        RetrofitClient.getClient(API.BASE_URL)?.create(IRetrofit::class.java)

    //사진검색 API호출을 위한
    fun searchPhotos(searchTerm: String?, completion: (RESPONSE_STATE, ArrayList<Photo>?) -> Unit) {

        val term = searchTerm.let {
            it
        } ?: ""


        //iRetrofit 클래스 아래에 있는 searchPhotos를 실행하는거다. 그리고 반환값이 call<JSON element>가된다.
        //term뜻은 내가 찾을 searchTerm(parameter) = 위에서 정의한 term이랑 같다는 뜻.
        val call = iRetrofit?.searchPhotos(searchTerm = term).let {
            it
        } ?: return

        call.enqueue(object : retrofit2.Callback<JsonElement> {
            override fun onResponse(call: Call<JsonElement>, response: Response<JsonElement>) {
                Log.d(
                    "TAG",
                    "RetrofitManager - onResponse() called() / response: ${response.body()}"
                )

                when (response.code()) {
                    200 -> {
                        response.body()?.let {
                            var parsedPhotoDataArray = ArrayList<Photo>()
                            val body = it.asJsonObject
                            val results = body.getAsJsonArray("results")
                            val total = body.get("totoal").asInt //search된 갯수가 나온다.

                            Log.d("TAG", "RetrofitManager - onResponse() called / total:$total")

                            results.forEach{ resultItem ->
                                val resultItemObject = resultItem.asJsonObject

                                val user = resultItemObject.get("user").asJsonObject

                                val username: String = user.get("username").asString

                                val likesCount = resultItemObject.get("likes").asInt

                                val thumbnailLink =
                                    resultItemObject.get("urls").asJsonObject.get("thumb").asString

                                val createdAt = resultItemObject.get("created_at").asString

                                val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                                val formatter = SimpleDateFormat("yyyy년\nMM월 dd일")
                                val outputDateString = formatter.format(parser.parse(createdAt))

//                                Log.d(
//                                    "TAG",
//                                    "RetrofitManager - outputDateString() : $outputDateString"
//                                )

                                val photoItem = Photo(
                                    author = username,
                                    likesCount = likesCount,
                                    thumbnail = thumbnailLink,
                                    createdAt = outputDateString,
                                )

                                parsedPhotoDataArray.add(photoItem)
                            }
                            completion(RESPONSE_STATE.OKAY, parsedPhotoDataArray)
                        }

                    }

                }

            }

            override fun onFailure(call: Call<JsonElement>, t: Throwable) {
                Log.d("TAG", "RetrofitManager - onFailure() called() / t: $t")
                completion(RESPONSE_STATE.FAIL, null)

            }

        })


    }


}