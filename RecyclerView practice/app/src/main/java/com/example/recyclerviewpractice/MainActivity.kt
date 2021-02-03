package com.example.recyclerviewpractice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.xwray.groupie.GroupieAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),MyRecyclerviewInterface {

    val TAG: String = "로그"

    //데이터를 담을 그릇 즉 배열
    //설명: modellist라는 array를 준비하고 안에들어있는건 mymodel이라는것이다.
    var modelList = ArrayList<MyModel>()

    private lateinit var myRecyclerAdapter: MyRecyclerAdpater

    //뷰가 화면에 그려질떄
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d(TAG, "MainActivity - onCreate() called")

        Log.d(TAG, "MainActivity - this.modelList.size: ${this.modelList.size}")
        //for 문을 쓰는 이유는 내가 넣을 데이터만큼 갯수를 정하려고
        for (i in 1..10) {
            //arraylist의 구성요소인 mymodel을 채워줄것이다
            val myModel = MyModel(
                name = "쩡대리 $i",
                profileImage = "https://img1.daumcdn.net/thumb/C100x100.mplusfriend/?fname=http%3A%2F%2Fk.kakaocdn.net%2Fdn%2FIxxPp%2FbtqC9MkM3oH%2FPpvHOkfOiOpKUwvvWcxhJ0%2Fimg_s.jpg"
            )

            //이제 만들어진걸 arraylist(var modelList)에 넣어줘야한다.
            this.modelList.add(myModel)
            //이클래스안에 있는 modelList에 my model을 넣어줘라
        }
        Log.d(TAG, "MainActivity - 반복문 후 this.modelList.size: ${this.modelList.size}")

        myRecyclerAdapter = MyRecyclerAdpater(this)
        myRecyclerAdapter.submitList(this.modelList)

            //recyclerview설정
        my_recycler_view.apply{

            layoutManager = LinearLayoutManager(this@MainActivity,LinearLayoutManager.VERTICAL,false)
            //어댑터 장착
            adapter = myRecyclerAdapter
        }


    }

    override fun onItemClicked() {
        Log.d(TAG, "MainActivity - onItemCLicked() called")

    }
}


