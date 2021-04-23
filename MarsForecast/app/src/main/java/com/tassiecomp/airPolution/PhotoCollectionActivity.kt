package com.tassiecomp.airPolution

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.tassiecomp.airPolution.model.Photo
import com.tassiecomp.airPolution.recyclerview.PhotoGridRecyclerViewAdapter
import kotlinx.android.synthetic.main.activity_photo_collection.*

class PhotoCollectionActivity: AppCompatActivity() {

    //데이터
    private var photoList = ArrayList<Photo>()
    //어답터
    private lateinit var photoGridRecyclerViewAdapter: PhotoGridRecyclerViewAdapter

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

        this.photoGridRecyclerViewAdapter = PhotoGridRecyclerViewAdapter()

        this.photoGridRecyclerViewAdapter.submitList(photoList)

        my_photo_recycler_view.layoutManager = GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false)

        my_photo_recycler_view.adapter = this.photoGridRecyclerViewAdapter


    } //


}