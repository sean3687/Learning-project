package com.example.myapplication

import android.app.ActivityManager
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.rockscissorpaper.R
import kotlinx.android.synthetic.main.activity_intent2.*

class AboutScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about_screen)

        homescreen

        }
    }
}