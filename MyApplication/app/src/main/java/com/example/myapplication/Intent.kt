package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_intent.*

class Intent1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intent)

        change_activity.setOnClickListener{

            val intent = Intent(this@Intent1, Intent2::class.java) //인텐트(요청)를 만든상태
            intent.putExtra()
            startActivity(intent)//요청을 보내는작업


        }
    }

    
}