package com.tassiecomp.deathcounter

import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_create_profile.*
import java.text.SimpleDateFormat
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

        //Datepicker Dialog setup
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)
        calanderButton_create.setOnClickListener() {
            val dpd = DatePickerDialog(
                this@CreateProfile,
                DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->

                    // Display Selected date in textbox
                    birthDay_create.setText("$year-${monthOfYear + 1}-$dayOfMonth")
                    c.set(Calendar.YEAR, year)
                    c.set(Calendar.MONTH, monthOfYear)
                    c.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                },
                year,
                month,
                day
            )
            dpd.show()

        }



        Save_create.setOnClickListener {
            Log.d("TAG", " button clicked")
            val userName = username_create.text.toString()
            val userAge = birthDay_create.text.toString()
            val userDie = dieAge_Create.text.toString()
            val dieAge = dieAge_Create.text.toString().toInt()
            var sf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")


            //set birthDate
            val BirthDate =
                "${c.get(Calendar.YEAR)}-${c.get(Calendar.MONTH) + 1}-${c.get(Calendar.DAY_OF_MONTH)} 00:00:00"
            Log.d("save", "BirthDate: $BirthDate")
            //set DieDate
            val dieAge_date =
                "${c.get(Calendar.YEAR) + dieAge}-${c.get(Calendar.MONTH) + 1}-${
                    c.get(
                        Calendar.DAY_OF_MONTH
                    )
                } 00:00:00"
            val userAge_int = ((System.currentTimeMillis()- sf.parse(BirthDate).time )/31556952000).toInt()
            Log.d("save","userAge_int: $userAge_int")

            // login error message
            when {
                userName.isEmpty() -> notification.text = "User Name is empty"
                userAge.isBlank() -> notification.text = "Please set your age"
                userDie.isBlank() -> notification.text = "Please set die age "

                //gotta compare two age in milisecond

//              "Your Age is bigger than die age"
                userAge_int > dieAge -> notification.text = "logically incorrect value"

                else -> { //execute save button

                    //Convert into milisecond

                    var Birthdate_milisecond = sf.parse(BirthDate).time
                    Log.d("save", "Birthdate_milisecond: $Birthdate_milisecond")

                    //convert into milisecond
                    var dieDate_milisecond = sf.parse(dieAge_date).time
                    Log.d("save", "dieDate_milisecond: $dieDate_milisecond")


                    //save to shared preference
                    val sharedPreference =
                        getSharedPreferences("CreateProfile", Context.MODE_PRIVATE)
                    val editor: SharedPreferences.Editor = sharedPreference.edit()
                    editor.putString("userName", userName)
                    editor.putString("DieDate", "${dieDate_milisecond}")
                    editor.putString("BirthDate", "$Birthdate_milisecond")
                    editor.commit()


                    //open new activity

                    finish()
                    val intent = Intent(this@CreateProfile, MainActivity::class.java)
                    startActivity(intent)

                }
            }


        }


    }


}




