package com.tassiecomp.instagram

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText

class EmailSignupActivity : AppCompatActivity() {

    lateinit var usernameView: EditText
    lateinit var userPassword1View:EditText
    lateinit var userPassword2View:EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_email_signup)

        initView(this@EmailSignupActivity)
    }

    fun initView(activity: Activity){
        usernameView = activity.findViewById(R.id.username_inputbox)
        userPassword1View = activity.findViewById(R.id.password2_inputbox)
        userPassword2View = activity.findViewById(R.id.username_inputbox)

    }

    fun getUserName():String {
        return usernameView.text.toString()
    }

    fun getUserPassword1():String {
        return getUserPassword1().toString()
    }

    fun getuserPassword2():String{
        return userPassword2View.text.toString()
    }




}