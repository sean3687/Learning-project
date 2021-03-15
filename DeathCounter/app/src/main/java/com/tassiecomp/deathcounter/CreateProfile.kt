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

        //sharedPreference setup
        val sharedPreference = getSharedPreferences("CreateProfile", Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPreference.edit()

        //brithday Datepicker Dialog setup
        val BirthCalander = Calendar.getInstance()
        val Birth_year = BirthCalander.get(Calendar.YEAR)
        val Birth_month = BirthCalander.get(Calendar.MONTH)
        val Birth_day = BirthCalander.get(Calendar.DAY_OF_MONTH)

        val DieCalander = Calendar.getInstance()
        val Die_year = DieCalander.get(Calendar.YEAR)
        val Die_month = DieCalander.get(Calendar.MONTH)
        val Die_day = DieCalander.get(Calendar.DAY_OF_MONTH)


        calanderButton_create.setOnClickListener() {
            val dpd = DatePickerDialog(
                this@CreateProfile,
                DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->

                    // Display Selected date in textbox
                    birthDay_create.setText("$year-${monthOfYear + 1}-$dayOfMonth")
                    val birthday: Calendar = Calendar.getInstance()
                    birthday.set(year, monthOfYear, dayOfMonth)

                    //shared preference
                    var Birthday_Millis = birthday.timeInMillis
                    editor.putLong("Birthday_Millis", Birthday_Millis)
                    editor.apply()

                },
                Birth_year,
                Birth_month,
                Birth_day
            )
            dpd.show()
        }

        calanderButton_create2.setOnClickListener() {
            val dpd = DatePickerDialog(
                this@CreateProfile,
                DatePickerDialog.OnDateSetListener { view2, year, monthOfYear, dayOfMonth ->

                    //set text on edit box
                    dieAge_Create.setText("$year-${monthOfYear + 1}-$dayOfMonth")

                    val dieday: Calendar = Calendar.getInstance()
                    dieday.set(year, monthOfYear, dayOfMonth)
                    var Dieday_Millis =
                        dieday.timeInMillis //when you change to Int it occurs problem
                    Log.d("save", "Die_Millis: $Dieday_Millis")

                    val editor: SharedPreferences.Editor = sharedPreference.edit()
                    editor.putLong("Die_Millis", Dieday_Millis) //정보를 넣는다.
                    editor.commit()


//                    MyApplication.sharedPreference.setInt("dieDay", Dieday_Millis)
//                    val save = MyApplication.sharedPreference.getInt("dieDay", 0)
//                    Log.d("save","sharedpreference saved: $save")

                },
                Die_year,
                Die_month,
                Die_day

            )
            dpd.show()
            dpd.getDatePicker().setMinDate(System.currentTimeMillis() + 86400000)
        }



        Save_create.setOnClickListener {
            Log.d("TAG", " button clicked")
            val userName = username_create.text.toString()
            val userAge = birthDay_create.text.toString()
            val userDie = birthDay_create.text.toString()

            Log.d("save", "userAge edit text:$userAge ")

            editor.putString("userName", userName)
            editor.apply()

            val Name = sharedPreference.getString("userName", null)
            val userAge_mili = sharedPreference.getLong("Birthday_Millis", 1)
            val userDie_mili = sharedPreference.getLong("Die_Millis", 1)


            Log.d("save", "saved name = $Name")
            Log.d("save", "saved userAge_mili = $userAge_mili")
            Log.d("save", "saved userDie_mili = $userDie_mili")


            // login error message
            when {
                userName.isEmpty() -> notification.text = "User Name is empty"
                userAge.isEmpty() -> notification.text = "Please set Birthday"
                userDie.isEmpty() -> notification.text = "Please set die day "

                //gotta compare two age in milisecond

//              "Your Age is bigger than die age"
                userAge_mili > userDie_mili -> notification.text = "logically incorrect value"

//
                else -> { //execute save button
                    Log.d("save", "logging in")
                    val editor: SharedPreferences.Editor = sharedPreference.edit()
                    editor.putLong("userAge_mili", userAge_mili)
                    editor.putLong("userDie_mili", userDie_mili)//정보를 넣는다.
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

fun main(args: Array<String>) {
    val BirthDate = "1998-11-11 00:00:00"
    var sf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
    var Birthdate_milisecond = sf.parse(BirthDate).time.toInt()

    println(Birthdate_milisecond)

}


