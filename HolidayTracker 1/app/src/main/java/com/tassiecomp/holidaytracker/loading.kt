package com.tassiecomp.holidaytracker

import android.app.PendingIntent.getActivity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import androidx.core.content.ContextCompat.startActivity

import kotlinx.android.synthetic.main.activity_loading.*
import java.util.*

class loading : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loading)

        loadingtitle.visibility = View.VISIBLE
        //loading our custom made animations
        val animation = AnimationUtils.loadAnimation(this, R.anim.fade_in)
        //starting the animation
        loadingtitle.startAnimation(animation)

        Timer().schedule(object : TimerTask() {

            override fun run() {
                startActivity(
                    Intent(this@loading, MainActivity::class.java)
                        .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                )
                finish()

            }
        }, 2000)

    }
}