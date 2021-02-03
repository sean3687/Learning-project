package com.example.holidaytracker


import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        val sdf = SimpleDateFormat("dd-MM-yyyy HH:mm:ss")
        var countDate = ""   //""05-01-2022 00:00:00"
        val now = Date()
        Log.d("string", "1. $now") //1. Wed Feb 03 00:36:25 GMT 2021

        try
        {
            val date = sdf.parse(countDate) //Fri Jan 01 00:00:00 KST 2021
            val currentTime = now.getTime()
            val destinationDate = date.getTime()
            Log.d("string", "2. $destinationDate") //2. 1641340800000

            val StartCountDown = destinationDate - currentTime
            Log.d("string", "3. $StartCountDown") //3. 29028082117
            daysleft_title_main.start(StartCountDown)
        }

        catch (e: ParseException) {
            e.printStackTrace()
        }
    }
}

fun main(args: Array<String>) {
    val sdf = SimpleDateFormat("dd-MM-yyyy HH:mm:ss")
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

        val holidaysListParse = sdf.parse(holidaysList_date[0])
        val destinationDate = holidaysListParse.getTime()
        val currentTime = Date()
        val currentDate = currentTime.getTime()
        println(holidaysListParse)
        println(destinationDate)


    if (currentDate holidaysList_date )
        (holidaysList_date[i])

}