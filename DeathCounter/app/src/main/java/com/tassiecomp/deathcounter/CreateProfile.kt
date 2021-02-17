package com.tassiecomp.deathcounter

import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_create_profile.*
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.lang.Double.parseDouble
import java.util.*

open class CreateProfile : AppCompatActivity() {


    //setting up internal data storage

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_profile)

        Log.d("TAG", "Create profile activity loaded")


        Save_create.setOnClickListener {
            Log.d("TAG"," button clicked")
            val userName = username_create.text.toString()
            val userAge = userAge_create.text.toString()
            val userDie = dieDate_create.text.toString()

            //set up for checking if age is numeric
            var numericAge = true
            var numericDie = true

            try {
                val num = parseDouble(userAge)
            } catch (e: NumberFormatException) {
                numericAge = false
            }

            try {
                val num = parseDouble(userDie)
            } catch (e: NumberFormatException) {
                numericDie = false
            }


            // login error message
            when {
                userName.isEmpty() -> notification.text = "User Name is empty"
                userAge.isEmpty() -> notification.text = "Please set your age"
                userDie.isEmpty() -> notification.text = "Please set die age "

                //check if age is Numeric
                //if it is not numeric
                !numericAge -> notification.text = "Please enter age in numeric(Age)"
                !numericDie -> notification.text = "Please enter age in numeric(Die age)"
                userDie.toInt() < userAge.toInt() -> notification.text =
                    "Your Age is bigger than die age"


                else -> { //execute save button

                    saveButton()
                    val intent = Intent(this@CreateProfile, MainActivity::class.java)

                }
            }


        }


    }

    //save button function
    private fun saveButton() {
        val userName = username_create.text.toString()
        val userAge = userAge_create.text.toString()
        val userDie = dieDate_create.text.toString()

        Log.d("TAG", "username: $userName")
        Log.d("TAG", "userAge: $userAge")
        Log.d("TAG", "userDie: $userDie")

        val sharedPreference = getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreference.edit()

        //save data
        editor.apply {
            putString(
                "userName",
                userName
            ) //"putString("name of key",value you are going to insert)"
            putInt("userAge", userAge.toInt())
            putInt("userDie", userDie.toInt())
        }.apply()
        editor.commit()

        Log.d("TAG", "sharedPreference: $sharedPreference")

    }


}




