package com.tassiecomp.airPolution

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.tassiecomp.airPolution.model.Photo

class PhotoCollectionActivity: AppCompatActivity() {

    var photoList = ArrayList<Photo>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo_collection)

        Log.d("TAG", "PhotoCollection activity - onCreated() called")

        val bundle = intent.getBundleExtra("array_bundle")
        val searchTerm = intent.getStringExtra("search_term")

        photoList = bundle?.getSerializable("photo_array_list") as ArrayList<Photo>

        Log.d("TAG","PhotoCollectionActivity - onCreate() called / searchTerm: $searchTerm, photoList.count() : ${photoList.count()}")



    } //


}