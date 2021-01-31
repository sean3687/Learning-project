package com.example.recyclerviewpractice

import android.content.ContentValues.TAG
import android.util.Log


class MyModel(var name: String? = null, var profileImage:String? = null) {
//null 빈값이어도 괜찮다
    val TAG: String = "로그"

    //기본 생성자자
    init {
        Log.d(TAG, "MyModel - init() called" )
    }
}