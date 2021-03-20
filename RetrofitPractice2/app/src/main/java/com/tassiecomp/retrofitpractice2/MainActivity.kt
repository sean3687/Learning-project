package com.tassiecomp.retrofitpractice2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://jsonplaceholder.typicode.com/"

class MainActivity : AppCompatActivity() {

    val TAG = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //bring base url
        getMyData()


    }

    fun getMyData() {

        //setting up base url
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(ApiInterface::class.java)

        //you get the data from retoriftBuilder.getData
        val retrofitData = retrofitBuilder.getData() // we are getting data from api interface

        //now in case the data doesn't load, we have to make code for whe api load succesful/ when it fail to load
        retrofitData.enqueue(object : Callback<List<myDataItem>?> {

            override fun onResponse(
                call: Call<List<myDataItem>?>,
                response: Response<List<myDataItem>?>
            ) {
                val responseBody = response.body()!! // what is this response? List from myDataItem is the response

                val myStringBuilder = StringBuilder()
                for(myData in responseBody) { // you get the all the data from response body
                    myStringBuilder.append(myData.id) // it brings value of myData Items you can assign what value to bring
                    myStringBuilder.append("\n")
                }

                txtId.text = myStringBuilder

            }

            override fun onFailure(call: Call<List<myDataItem>?>, t: Throwable) {

                Log.d(TAG,"onFailure: "+t.message)


            }
        })
    }
}