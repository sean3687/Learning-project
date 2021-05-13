package com.tassiecomp.firebasechatcopy


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*
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
            performRegister()
        }

        already_have_account_text_view.setOnClickListener{
            Log.d("MainActivity", "Try to show login activity")

            //launch the login activity somehow
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }


    }
    private fun performRegister() {
        val email = email_editText_register.text.toString()
        val password = password_editText_register.text.toString()

        Log.d("Login", "Attempt login with email/pw: $email/***")

        if(email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please enter text in email/password", Toast.LENGTH_SHORT).show()
            return// go back to
        }
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email,password) //it load string from edittexts
            .addOnCompleteListener{
                if(!it.isSuccessful) return@addOnCompleteListener // exception(when it fail) return  top of add oncomplete listener

                //else if successful
                Log.d("Main","Successfully created user with uid: ${it.result?.user?.uid}")
            }
            .addOnFailureListener{
                Toast.makeText(this,"lease enter text in email/password", Toast.LENGTH_SHORT).show()
                Log.d("Main","Failed to create user: ${it.message}")
            }
    }
}