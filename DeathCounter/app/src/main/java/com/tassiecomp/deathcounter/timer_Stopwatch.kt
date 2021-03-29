package com.tassiecomp.deathcounter

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.tassiecomp.deathcounter.stopwatch.loopTimer
import com.tassiecomp.deathcounter.stopwatch.stop_watch
import com.tassiecomp.deathcounter.stopwatch.timer
import com.tassiecomp.deathcounter.stopwatch.viewPageAdapter
import kotlinx.android.synthetic.main.activity_timer_stopwatch.*

class timer_Stopwatch : AppCompatActivity() {
    override fun onPause() {
        super.onPause()
        overridePendingTransition(0, 0)

    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timer_stopwatch)


        backspace.setOnClickListener {
            finish()
        }

        chipNavigationBar.setOnItemSelectedListener { id ->
            when (id) {
                R.id.stopwatch -> viewpager.currentItem = 0
                R.id.timer -> viewpager.currentItem = 1
                R.id.Looptimer -> viewpager.currentItem = 2
            }
        }

        viewpager.setOnTouchListener(View.OnTouchListener{ v,event -> true})
        viewpager.adapter = viewPageAdapter(supportFragmentManager).apply{

        }


    }
}