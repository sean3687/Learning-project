package com.tassiecomp.deathcounter

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_create_profile.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setContentView(R.layout.activity_create_profile)

        //checking if want to register
        val openCreateProfile = Intent(this@MainActivity, CreateProfile::class.java) //인텐트(요청)를 만든상태
        val userName = username_create.text.toString()

        when {
            userName.isEmpty()-> {
                Log.d("TAG","Empty")
            }
            else -> {} //nothing happen
        }

    }
}