package com.tassiecomp.airPolution

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.tassiecomp.airPolution.model.Photo

class PhotoCollectionActivity: AppCompatActivity() {

    private var photoList = ArrayList<Photo>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo_collection)

        Log.d("TAG", "PhotoCollection activity - onCreated() called")

        //array bundle의 이름으로 가져온다.
        val bundle = intent.getBundleExtra("array_bundle")

        //search term도 가져온다.
        val searchTerm = intent.getStringExtra("search_term")

        //포토리스트안에 serializable한 번들을 넣어준다.
        photoList = bundle?.getSerializable("photo_array_list") as ArrayList<Photo>

        Log.d("TAG","PhotoCollectionActivity - onCreate() called / searchTerm: $searchTerm, photoList.count() : ${photoList.count()}")



    } //


}