package com.tassiecomp.airPolution

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.tassiecomp.airPolution.api.GitHubService
import retrofit2.Retrofit


class MainActivity : AppCompatActivity() {

    val APIkey =
    val lat =
    val lon =
    val BASE_URL =
        "http://api.openweathermap.org/data/2.5/air_pollution?lat=${lat}&lon=${lon}&appid=${APIkey}"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .build()

        val service = retrofit.create(GitHubService::class.java)

    }
}