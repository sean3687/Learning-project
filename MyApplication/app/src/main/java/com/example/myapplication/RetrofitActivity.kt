package com.example.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_retrofit)

        val retrofit = Retrofit.Builder()
            .baseUrl("http://mellowcode.org") //여기에 baseurl을 써준다. http://mellowcode.org/json/students/ 여기서 앞부분만.
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}