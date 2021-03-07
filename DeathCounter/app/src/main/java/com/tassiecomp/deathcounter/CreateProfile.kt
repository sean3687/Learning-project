package com.tassiecomp.deathcounter

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.DatePicker
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_create_profile.*
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.lang.Double.parseDouble
import java.sql.ResultSetMetaData
import java.util.*

open class CreateProfile : AppCompatActivity() {


    //setting up internal data storage

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_profile)

        val numberFromMain = intent.getIntExtra("from", 0)
        Log.d("TAG-CreateProfile", "$numberFromMain")
        if (numberFromMain == 0) {
            title_create.text = "Create Profile"
        } else {
            title_create.text = "Edit Profile"
            Save_create.text = "Done"

        }
        Log.d("TAG", "Create profile activity loaded")


//when you click edit text area of date assigning part, you datepicker dialog will show up
        userAge_create.setOnClickListener(){

        }

        dieDate_create.setOnClickListener(){

        }



        Save_create.setOnClickListener {
            Log.d("TAG", " button clicked")
            val userName = username_create.text.toString()
            val userAge  = userAge_create.text.toString()
            val userDie = dieDate_create.text.toString()


            // login error message
            when {
                userName.isEmpty() -> notification.text = "User Name is empty"
                userAge.isEmpty() -> notification.text = "Please set your age"
                userDie.isEmpty() -> notification.text = "Please set die age "

                //gotta compare two age in milisecond
//                userDie.toInt() < userAge.toInt() -> notification.text =
//                    "Your Age is bigger than die age"


                else -> { //execute save button

                    saveButton()
                    finish()
                    val intent = Intent(this@CreateProfile, MainActivity::class.java)
                    startActivity(intent)

                }
            }


        }


    }

    //save button function

    open fun saveButton() {
        val userName = username_create.text.toString()
        //gotta save below value as milisecond
//        val userAge = userAge_create.text.toString()
//        val userDie = 80

        Log.d("TAG", "username: $userName")
//        Log.d("TAG", "userAge: $userAge")
//        Log.d("TAG", "userDie: $userDie")

        val sharedPreference = getSharedPreferences("CreateProfile", Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPreference.edit()

        editor.putString("userName", userName)
//        editor.putInt("userAge", userAge.toInt())
//        editor.putInt("userDie", userDie.toInt())
        editor.apply()

        Log.d("TAG", "sharedPreference: $sharedPreference")

    }


}




