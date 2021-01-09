package com.example.myapplication

import android.telecom.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST


//주소에서 말단을 관리할수있는 서비스 클래스 http://mellowcode.org이 주소에 뒷부분을 추가하는부분
interface RetrofitService{

    @GET("json/students/")
    fun getStudentsList(): Call<ArrayList<PersonFromServer>>

    @POST("json/students/")
    fun createStudent(
        @Body params: HashMap<String, Any>
    ): Call<PersonFromServer>

    @POST("json/studnets")
    fun createStudentEasy(
        @Body params: PersonFromServer
    ): Call<PersonFromServer>
}