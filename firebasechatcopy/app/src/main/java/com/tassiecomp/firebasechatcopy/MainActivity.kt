package com.tassiecomp.firebasechatcopy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val email = email_editText_register.text.toString()
        val password = password_editText_register.text.toString()

        Log.d("MainActivity", "Email is "+ email)
        Log.d("MainActivity", "Password is "+ password)

        register_button_register.setOnClickListener{

        }

        already_have_account_text_view.setOnClickListener{
            Log.d("MainActivity", "Try to show login activity")
        }


    }
}