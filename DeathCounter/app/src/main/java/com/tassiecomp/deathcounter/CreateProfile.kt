package com.tassiecomp.deathcounter

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.DateValidatorPointBackward
import com.google.android.material.datepicker.DateValidatorPointForward
import com.google.android.material.datepicker.MaterialDatePicker
import kotlinx.android.synthetic.main.activity_create_profile.*
import java.text.SimpleDateFormat
import java.util.*


open class CreateProfile : AppCompatActivity() {

    override fun onPause() {
        super.onPause()
        overridePendingTransition(0, 0)
    }

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
            sub_title.text = "If you don't want to change profile data, press backspace"
            Save_create.text = "Done"

        }
        Log.d("TAG", "Create profile activity loaded")


//when you click edit text area of date assigning part, you datepicker dialog will show up

        //sharedPreference setup
        val sharedPreference = getSharedPreferences("CreateProfile", Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPreference.edit()

        //brithday Datepicker Dialog setup

        val DieCalander = Calendar.getInstance()
        val Die_year = DieCalander.get(Calendar.YEAR)
        val Die_month = DieCalander.get(Calendar.MONTH)
        val Die_day = DieCalander.get(Calendar.DAY_OF_MONTH)

//        buttonPickDate.setOnClickListener {
//
//            // Create the date picker builder and set the title
//            val builder = MaterialDatePicker.Builder.datePicker()
//                .also {
//                    title = "Pick Date"
//                }
//
//
//            // create the date picker
//            val datePicker = builder.build()
//
//            // set listener when date is selected
//            datePicker.addOnPositiveButtonClickListener {
//
//                // Create calendar object and set the date to be that returned from selection
//                val calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"))
//                calendar.time = Date(it)
//                textView.text = "${calendar.get(Calendar.DAY_OF_MONTH)}- " +
//                        "${calendar.get(Calendar.MONTH) + 1}-${calendar.get(Calendar.YEAR)}"
//
//            }
//
//            datePicker.show(supportFragmentManager, "MyTAG")
//
//        }


        calanderButton_create.setOnClickListener() {
            val calendarConstraintBuilder = CalendarConstraints.Builder()
            calendarConstraintBuilder.setValidator(DateValidatorPointBackward.now())

            val builder = MaterialDatePicker.Builder.datePicker()
                .setTitleText("Set BirthDay")
                .setInputMode(MaterialDatePicker.INPUT_MODE_TEXT)
                .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                .setCalendarConstraints(calendarConstraintBuilder.build());

            val datePicker = builder.build()

            datePicker.addOnPositiveButtonClickListener {
                    //shared preference
                    val calendar = Calendar.getInstance()
                    calendar.time = Date(it)
                    val calendarMilli = calendar.timeInMillis
                    calanderButton_create.text = "${calendar.get(Calendar.MONTH) + 1}/${calendar.get(Calendar.DAY_OF_MONTH)}/${calendar.get(Calendar.YEAR)}"
                    editor.putLong("Birthday_Millis", calendarMilli)
                    editor.apply()
                Log.d("Birthday_Millis", "$calendarMilli")
            }
            datePicker.show(supportFragmentManager,datePicker.toString())

        }

        calanderButton_create2.setOnClickListener() {

            val calendarConstraintBuilder = CalendarConstraints.Builder()
            calendarConstraintBuilder.setValidator(DateValidatorPointForward.now())

            val builder = MaterialDatePicker.Builder.datePicker()
                .setTitleText("Set Dieday")
                .setInputMode(MaterialDatePicker.INPUT_MODE_TEXT)
                .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                .setCalendarConstraints(calendarConstraintBuilder.build());



            val datePicker = builder.build()

            datePicker.addOnPositiveButtonClickListener {
                //shared preference
                val calendar = Calendar.getInstance()
                calendar.time = Date(it)
                val calendarMilli = calendar.timeInMillis
                calanderButton_create2.text = "${calendar.get(Calendar.MONTH) + 1}/${calendar.get(Calendar.DAY_OF_MONTH)}/${calendar.get(Calendar.YEAR)}"
                editor.putLong("Die_Millis", calendarMilli)
                editor.apply()
                Log.d("Die_Millis", "$calendarMilli")
            }
            datePicker.show(supportFragmentManager,datePicker.toString())




//            val dpd = DatePickerDialog(
//                this@CreateProfile,
//                DatePickerDialog.OnDateSetListener { view2, year, monthOfYear, dayOfMonth ->
//
//                    //set text on edit box
//                    calanderButton_create.setText("$year-${monthOfYear + 1}-$dayOfMonth")
//
//                    val dieday: Calendar = Calendar.getInstance()
//                    dieday.set(year, monthOfYear, dayOfMonth)
//                    var Dieday_Millis =
//                        dieday.timeInMillis //when you change to Int it occurs problem
//                    Log.d("save", "Die_Millis: $Dieday_Millis")
//
//                    val editor: SharedPreferences.Editor = sharedPreference.edit()
//                    editor.putLong("Die_Millis", Dieday_Millis) //정보를 넣는다.
//                    editor.commit()
//
//
////                    MyApplication.sharedPreference.setInt("dieDay", Dieday_Millis)
////                    val save = MyApplication.sharedPreference.getInt("dieDay", 0)
////                    Log.d("save","sharedpreference saved: $save")
//
//                },
//                Die_year,
//                Die_month,
//                Die_day
//
//            )
//            dpd.show()
//            dpd.getDatePicker().setMinDate(System.currentTimeMillis() + 86400000)
        }



        Save_create.setOnClickListener {
            Log.d("TAG", " button clicked")
            val userName = username_create.text.toString()
            val userAge = calanderButton_create.text.toString()
            val userDie = calanderButton_create2.text.toString()

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


