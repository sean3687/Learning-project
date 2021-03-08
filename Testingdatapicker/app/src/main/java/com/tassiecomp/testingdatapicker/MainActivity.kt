package com.tassiecomp.testingdatapicker

import android.app.DatePickerDialog
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.util.*
import android.widget.DatePicker





class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sharedPreference = getSharedPreferences("Birthdate", Context.MODE_PRIVATE)
        //this is initial date going to show when datepicker dialog is open



        //calender setup
        createprofile.setOnClickListener {

            var c = Calendar.getInstance()
            var year = c.get(Calendar.YEAR)
            var month = c.get(Calendar.MONTH)
            var day = c.get(Calendar.DAY_OF_MONTH)

            val dpd = DatePickerDialog(
                this@MainActivity,
                DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->

                    // Display Selected date in textbox
                    createprofile.setText("$year-${monthOfYear + 1}-$dayOfMonth")
                    c.set(Calendar.YEAR, year)
                    c.set(Calendar.MONTH, monthOfYear)
                    c.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                },
                year,
                month,
                day
            )
            dpd.show()
            return calendar.getTime()

        }

        save_button.setOnClickListener() {
            DatePicker.getDate()
            log
            val BirthDate = "$year-${month + 1}-$day 00:00:00"
            var sf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
            var date = sf.parse(BirthDate)
            var dieAge = die_age.text.toString().toInt()
            var dieAgein_date = sf.parse("${year + dieAge}-${month + 1}-$day 00:00:00")
            var calculateDate = (dieAgein_date.time - date.time)
            Log.d("save", "saved birthdate = $BirthDate")
            Log.d("save", "calculateDate = $calculateDate")

            val editor: SharedPreferences.Editor = sharedPreference.edit()
            //여기까지가 editor를 불러와서 string을 넣어주기까지 defualt setup
            editor.putString("value", "$calculateDate") //정보를 넣는다.
            editor.commit()
        }

    }
}

fun DatePicker.getDate(): Date {
    val calendar = Calendar.getInstance()
    calendar.set(year, monthOfYear, dayOfMonth)
    return calendar.time
}



