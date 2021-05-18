package com.tassiecomp.kotlinmessanger

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigation.setOnNavigationItemSelectedListener{
            when(it.itemId) {
                R.id.navigation_people -> {
                    //TODO: show people fragment
                    true

                }
                R.id.navigation_my_account -> {
                    //TODO: Show my account fragment
                    true
                }
                else -> false
            }
        }
    }
}