package com.tassiechat.messages

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.tassiechat.R

class chatLogActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_log)

        supportActionBar?.title = "chat Log"
    }
}