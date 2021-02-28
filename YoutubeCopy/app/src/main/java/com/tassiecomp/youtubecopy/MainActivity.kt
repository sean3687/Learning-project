package com.tassiecomp.youtubecopy


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import java.io.IOException

class MainActivity() : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView_main.layoutManager = LinearLayoutManager(this)
//        recyclerView_main.adapter = MainAdapter()

        fetchJson()
    }

    //connecting json
    fun fetchJson() {
        println("Attempting to Fetch JSON")

        //1. create value of url
        val url = "https://api.letsbuildthatapp.com/youtube/home_feed"

        //2. downloading url(it's on okhttp instruction)
        val request = Request.Builder().url(url).build()
        val client = OkHttpClient()


        client.newCall(request).enqueue(object : Callback {
            override fun onResponse(call: Call, response: Response) {
                //body로 url에서 json파일을 가져왔다.
                val body = response.body?.string() //it brings contents in json file in string
                println(body)

                //now we have to convert JSON to readable
                val gson = GsonBuilder().create()

                //by using gson.fromJson body string is changing to Gson
                val homeFeed = gson.fromJson(body, Models.HomeFeed::class.java)



                runOnUiThread{
                    recyclerView_main.adapter= MainAdapter(homeFeed)
                }
            }

            override fun onFailure(call: Call, e: IOException) {
                println("Failed to excute request")
            }

        })
    }
}


// converting json file to int and strings
// but how do we convert this?? -> use GSON Library

//    class HomeFeed(val videos: List<Video>)

// now we are trying to bring all the component of video list

//    class Video(val id: Int, val name: String, val link: String, val imageUrl: String, numberOfViews: Int,
//    val channel: Channel)

// you have to mention channel here
// make another class for channel too

//    class Channel(val name:String, val profileImageUrl: String)

