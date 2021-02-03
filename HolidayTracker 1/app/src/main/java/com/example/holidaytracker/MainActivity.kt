package com.example.holidaytracker


import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity() {
    val displayListDate = mutableListOf<Long>()
    val displayListName = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val holidaysList_date = arrayOf(
            "01-01-2021 00:00:00",
            "18-01-2021 00:00:00",
            "31-05-2021 00:00:00",
            "05-07-2021 00:00:00",
            "07-09-2021 00:00:00",
            "11-11-2021 00:00:00",
            "25-11-2021 00:00:00",
            "24-12-2021 00:00:00",
            "31-12-2021 00:00:00"
        )
        val holidayList_name = arrayOf(
            "New Year's Day",
            "Martin Luther King Jr.Day",
            "Memorial Day",
            "Independence Day",
            "Labor Day",
            "Veterans Day",
            "Thanksgiving",
            "Christmas Day",
            "New Year's Day"
        )

//making list

//        val sdf = SimpleDateFormat("dd-MM-yyyy HH:mm:ss")
//        var countDate = ""   //""05-01-2022 00:00:00"
//        val now = Date()
//        Log.d("string", "1. $now") //1. Wed Feb 03 00:36:25 GMT 2021


        for (i in 0..8) {
            val sdf = SimpleDateFormat("dd-MM-yyyy HH:mm:ss")
            val holidaysList = sdf.parse(holidaysList_date[i]).getTime()
            val currentTime = Date()
            val currentDate = currentTime.getTime()

            if (holidaysList > currentDate) {

                displayListDate.add(holidaysList)
                displayListName.add(holidayList_name[i])

            } else {

            }
        }

        try {

            val StartCountDown = displayListDate[0] - Date().getTime()
            Log.d("TAG2", "3. $StartCountDown") //3. 29028082117
            holiday_title_main.setText(displayListName[0])
            daysleft_title_main.start(StartCountDown)
        } catch (e: ParseException) {
            e.printStackTrace()
        }

    }
}







