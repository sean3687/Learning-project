package com.tassiecomp.firebasechatcopy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)



        login_button_login.setOnClickListener{
            val email = email_editText_login.text.toString()
            val password = password_editText_login.text.toString()

            FirebaseAuth.getInstance().signInWithEmailAndPassword(email,password)
//                .addOnCompleteListener()

        }

        back_to_register_textview.setOnClickListener{
            finish()
        }



    }


}