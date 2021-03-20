package com.tassiecomp.randomquotegenerator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.awaitResponse
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.lang.Exception


const val BASE_URL = "https://cat-fact.herokuapp.com"

class MainActivity : AppCompatActivity() {

    private var TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getCurrentData()


        layout_generate_new_fact.setOnClickListener {
            getCurrentData()
        }
    }

    private fun getCurrentData() {
        //this is before app make request
        tv_textView.visibility = View.INVISIBLE
        tv_timeStamp.visibility = View.INVISIBLE
        progressBar.visibility = View.VISIBLE


        //api 불러오기 base url setup
        val api = Retrofit.Builder().baseUrl(BASE_URL)
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiRequests::class.java)

        GlobalScope.launch(Dispatchers.IO) {

            try {

                //summon getcatFact api
                val response = api.getCatFact().awaitResponse()
                //if summon is unsuccessful
                if (response.isSuccessful) {
                    val data = response.body()!!
                    Log.d(TAG, data.text)

                    withContext(Dispatchers.Main) {
                        tv_textView.visibility = View.VISIBLE
                        tv_timeStamp.visibility = View.VISIBLE
                        progressBar.visibility = View.GONE

                        tv_textView.text = data.text
                        tv_timeStamp.text = data.createdAt
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {

                    Toast.makeText(
                        applicationContext,
                        "Seems like something went wrong",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }

    }
}