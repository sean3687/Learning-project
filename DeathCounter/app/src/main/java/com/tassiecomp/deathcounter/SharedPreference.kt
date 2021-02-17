package com.tassiecomp.deathcounter

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class SharedPreference : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shared_preference)

        //SharedPreference
        val sharedPreference = getSharedPreferences("sp1", Context.MODE_PRIVATE)
        //mode
        //- MODE_PRIVATE 생성한 application에서만 사용가능
        //- MODE_WORLD_READABLE: 다른 어플리케이션에서 읽기가능
        //- MODE-WORLD_WRITEABLE: 다른 어플리케이션에서 기록도 가능
    }
}