package com.tassiechat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        register_button_register.setOnClickListener{
            val email = email_edittext_register.text.toString()
            val password = password_edittext_register.text.toString()
            Log.d("MainActivity", "Email is: " + email)
            Log.d("MainActivity", "Password: $password")

        }

        textview_account.setOnClickListener{
            Log.d("MainActivity", "try to show login activity")
            val  intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}