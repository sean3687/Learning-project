package com.tassiecomp.holidaytracker

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.ads.MobileAds
import kotlinx.android.synthetic.main.activity_main.*
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import com.google.android.gms.ads.AdRequest;


class MainActivity : AppCompatActivity() {
    var displayListDate = mutableListOf<Long>()
    var displayListName = mutableListOf<String>()
    var displayDaysLeft = mutableListOf<Long>()

    //recycler view contents
    var displayDaysLeft_sub = mutableListOf<String>()
    var displayMDformat_sub = mutableListOf<String>()
    var displayIcon_sub = mutableListOf<Any>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Google ad Initilize
        MobileAds.initialize(this, "ca-app-pub-8358259317968297/2871421759")

        adView.loadAd(AdRequest.Builder().build())

        //adding holiday
        //1. add image on drawable folder
        //add R.drawable displayicon list
        //2. add holiday sub date
        //3. add holiday json sytle date
        //4. add holiday name
        //5. change 'for' counts
        val displayIcon = intArrayOf(
            //Jan
            R.drawable.newyear,
            R.drawable.martin,
            //Feb
            R.drawable.valentines,
            //Mar
            //April
            R.drawable.easter,
            //May
            R.drawable.mothers,
            R.drawable.memorial,
            //Jun
            R.drawable.fathers,
            //Jul
            R.drawable.independence,
            //Aug
            //sep
            R.drawable.labor,
            //Oct
            //Nov
            R.drawable.veterans,
            R.drawable.thanks,
            //Dec
            R.drawable.christmas,
            R.drawable.newyear

        )


        val HolidaysList_sub = arrayOf(
            //Jan
            "Sat Jan 1",
            "Mon Jan 18",
            //Feb
            "Sun Feb 14",
            //Mar
            //April
            "Sun Apr 4",
            //May
            "Sun May 9",
            "Mon May 31",
            //Jun
            "Sun Jun 20",
            //Jul
            "Mon Jul 05",
            //Aug
            //sep
            "Tue Sep 07",
            //Oct
            //Nov
            "Thu Nov 11",
            "Thu Nov 25",
            //Dec
            "Mon Dec 26",
            "Sat Dec 31"


        )
        val holidaysList_date = arrayOf(
            //Jan
            "01-01-2021 00:00:00",
            "18-01-2021 00:00:00",
            //Feb
            "14-02-2021 00:00:00",
            //Mar
            //April
            "04-04-2021 00:00:00",
            //May
            "09-05-2021 00:00:00",
            "31-05-2021 00:00:00",
            //Jun
            "20-06-2021 00:00:00",
            //Jul
            "05-07-2021 00:00:00",
            //Aug
            //sep
            "07-09-2021 00:00:00",
            //Oct
            //Nov
            "11-11-2021 00:00:00",
            "25-11-2021 00:00:00",
            //Dec
            "24-12-2021 00:00:00",
            "31-12-2021 00:00:00",

            )
        val holidayList_name = arrayOf(

            //Jan
            "New Year's Day",
            "MLK Jr.Day",
            //Feb
            "Valentine's Day",
            //Mar
            //April
            "Easter Sunday",
            //May
            "Mother's Day",
            "Memorial Day",
            //Jun
            "Father's Day",
            //Jul
            "Independence Day",
            //Aug
            //sep
            "Labor Day",
            //Oct
            //Nov
            "Veterans Day",
            "Thanksgiving",
            //Dec
            "Christmas Day",
            "New Year's Day",
            )

//making list

//        val sdf = SimpleDateFormat("dd-MM-yyyy HH:mm:ss")
//        var countDate = ""   //""05-01-2022 00:00:00"
//        val now = Date()
//        Log.d("string", "1. $now") //1. Wed Feb 03 00:36:25 GMT 2021


        for (i in 0..12) {
            val sdf = SimpleDateFormat("dd-MM-yyyy HH:mm:ss")
            val holidaysList = sdf.parse(holidaysList_date[i]).getTime()
            val currentTime = Date()
            val currentDate = currentTime.getTime()




            if (holidaysList > currentDate) {

                displayListDate.add(holidaysList)
                displayListName.add(holidayList_name[i])
                displayMDformat_sub.add(HolidaysList_sub[i])
                displayIcon_sub.add(displayIcon[i])

            } else {

            }
        }
        //making days difference List
        for (i in displayListDate.indices) {
            val StartCountDown = displayListDate[i] - Date().getTime()
            val startCountDown_sub =
                "D-${((displayListDate[i] - Date().getTime()) * 1.1574E-8).toInt()}"
            displayDaysLeft.add(StartCountDown)
            displayDaysLeft_sub.add(startCountDown_sub)

        }


        Log.d("TAG", "$displayDaysLeft_sub")
        // title count down


        try {
            daysleft_title_main.start(displayDaysLeft[0]) // start count down number
            holiday_title_main.setText(displayListName[0])
            holiday_image_main.setImageResource(displayIcon_sub[0] as Int)
//            holder.iconSub.setImageResource(icon[position] as Int)
        } catch (e: ParseException) {
            e.printStackTrace()
        }

//make recyclerView
        recycler_sub_main.layoutManager = LinearLayoutManager(this)
        recycler_sub_main.adapter = RecyclerAdapter(
            displayDaysLeft_sub,
            displayListName,
            displayMDformat_sub,
            displayIcon_sub
        )


    }


}








