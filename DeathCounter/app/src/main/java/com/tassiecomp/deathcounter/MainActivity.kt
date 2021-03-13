package com.tassiecomp.deathcounter

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import com.mikhaellopez.circularprogressbar.CircularProgressBar
import kotlinx.android.synthetic.main.activity_create_profile.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.nav_header.*
import java.math.BigDecimal
import java.math.RoundingMode
import java.util.*


class MainActivity : AppCompatActivity() {

    lateinit var toggle: ActionBarDrawerToggle


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        toggle = ActionBarDrawerToggle(this, drawerlayout, R.string.open, R.string.close)
        drawerlayout.addDrawerListener(toggle)
        toggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val intent = Intent(this@MainActivity, CreateProfile::class.java)
        intent.putExtra("from", 1)

        var sharedPreference = getSharedPreferences("CreateProfile", Context.MODE_PRIVATE)
        val userBirth_age = (sharedPreference.getLong("userAge_mili", 1))/31556926000
        val userDie_age = sharedPreference.getLong("userDie_mili", 1)/31556926000

        //checking if registered
        val savedUserName = sharedPreference.getString("userName", null)
        when {
            savedUserName === null -> {
                Log.d("TAG", "Empty")
                val intent = Intent(this@MainActivity, CreateProfile::class.java)
                intent.putExtra("from", 0)
                startActivity(intent)

            }
            else -> {
                Log.d("TAG", "there is value")
                Log.d("TAG", "saved value: $savedUserName")
                //drawer settings
                userName_title.setText("$savedUserName")
                userStates_title.setText("$userBirth_age $userDie_age")


            }
        }
        //drawer menu button

        main_openDrawer.setOnClickListener {

            drawerlayout.openDrawer(GravityCompat.START)
        }




        navView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.profileIcon ->
                    startActivity(intent)


                R.id.settingIcon -> Toast.makeText(
                    applicationContext,
                    "Clicked setting", Toast.LENGTH_SHORT
                ).show()
                R.id.helpIcon -> Toast.makeText(
                    applicationContext,
                    "Clicked help", Toast.LENGTH_SHORT
                ).show()

            }
            true
        }


        //circular progress bar

        val circularProgressBar = findViewById<CircularProgressBar>(R.id.circularProgressBar)
        circularProgressBar.apply {
            // Set Progress (setting up initial progress)
            progress = 0f
            // or with animation, show end progress with animation
            setProgressWithAnimation(45f, 1000) // =1s

            // Set Progress Max
            progressMax = 100f

            // or with gradient
            progressBarColorStart = Color.parseColor("#DBA2EE")
            progressBarColorEnd = Color.parseColor("#38CDFF")
            progressBarColorDirection = CircularProgressBar.GradientDirection.TOP_TO_BOTTOM

            // Set background ProgressBar Color
            backgroundProgressBarColor = Color.parseColor("#e6eef5")

            // Set Width
            progressBarWidth = 15f // in DP
            backgroundProgressBarWidth = 40f // in DP

            // Other
            roundBorder = true
            startAngle = 0f
            progressDirection = CircularProgressBar.ProgressDirection.TO_RIGHT
        }

        //initial setup


        //stopped at bring percentage from cal culate milisecond function
        my_life_left.setOnClickListener {
            CalculateLife()
        }

        my_year_left.setOnClickListener {
            CalculateYear()
        }

        my_month_left.setOnClickListener {
            CalculateMonth()
        }
        my_week_left.setOnClickListener {
            CalculateWeek()
        }


    }

    fun CalculateLife() {
        val ProgressbarLifePercent = calculateMilisecond().lifePercent.toFloat()
        val ProgressbarLifeSecond = calculateMilisecond().life
        val yearLeft = ProgressbarLifeSecond / 31556926000
        Log.d("Progress", "$yearLeft")
        val daysLeft =
            (ProgressbarLifeSecond.rem(31556926000))/86400000
        Log.d("Progress", "$daysLeft")
//
        if (yearLeft.toInt() == 0) {
            if (daysLeft.toInt() == 0) {
                countdownView_title.setText("Are you there?")
            } else {
                countdownView_title.setText("${daysLeft}Days") //여기 가문제
            }
        } else {
            if (daysLeft.toInt() == 0) {
                countdownView_title.setText("${yearLeft}Years")
            } else {
                countdownView_title.setText("${yearLeft}Years ${daysLeft}Days")
            }

        }
        statement_title.setText("My life end in")
        Percent_title.setText("${ProgressbarLifePercent}%")
        circularProgressBar.setProgressWithAnimation(ProgressbarLifePercent, 1000)


    }

    fun CalculateYear() {
        val ProgressbarYearPercent = calculateMilisecond().yearPercent.toFloat()
        val ProgressbarYearSecond = calculateMilisecond().year
        val daysLeft = (ProgressbarYearSecond / 86400000)
        if (daysLeft.toInt() == 1) {
            countdownView_title.setText("${daysLeft}Day")
        } else {
            if (daysLeft.toInt() == 0)
                countdownView_title.setText("Today")
            else {
                countdownView_title.setText("${daysLeft}Days")
            }
        }


        circularProgressBar.setProgressWithAnimation(ProgressbarYearPercent, 1000)
        statement_title.setText("This Year end in")
        Percent_title.setText("${ProgressbarYearPercent}%")

    }

    fun CalculateMonth() {
        val ProgressbarMonthPercent = calculateMilisecond().monthPercent.toFloat()
        val ProgressbarMonthSecond = calculateMilisecond().month
        val daysLeft = ProgressbarMonthSecond / 86400000

        if (daysLeft.toInt() == 1) {
            countdownView_title.setText("${daysLeft}Day")
        } else {
            if (daysLeft.toInt() == 0)
                countdownView_title.setText("Today")
            else {
                countdownView_title.setText("${daysLeft}Days")
            }
        }

        circularProgressBar.setProgressWithAnimation(ProgressbarMonthPercent, 1000)
        statement_title.setText("This Month end in")
        Percent_title.setText("${ProgressbarMonthPercent}%")
    }

    fun CalculateWeek() {
        val ProgressbarWeekPercent = calculateMilisecond().weekPercent.toFloat()
        val ProgressbarWeekSecond = 604800000 - calculateMilisecond().week

        val daysLeft = ProgressbarWeekSecond / 86400000
        val hoursLeft = ProgressbarWeekSecond.rem(86400000) / 3600000

        Log.d("weekClicked", "$daysLeft")
        Log.d("weekClicked", "$hoursLeft")

        if (daysLeft.toInt() == 0) {
            if (hoursLeft.toInt() <= 1)//00
                countdownView_title.setText("In an Hour")
            else {//01
                countdownView_title.setText("${hoursLeft} Hours")
            }
        } else {
            if (hoursLeft.toInt() <= 1)//10
                countdownView_title.setText("${daysLeft}Day")
            else {//11
                countdownView_title.setText("${daysLeft}Days ${hoursLeft}Hours")
            }
        }

        circularProgressBar.setProgressWithAnimation(ProgressbarWeekPercent, 1000)
        statement_title.setText("This Week end in")
        Percent_title.setText("${ProgressbarWeekPercent}%")


    }


    fun calculateMilisecond(): TimeData {
        //Units
        val dayinMilli = 86400000
        val weekInMilli = 604800000
        val yearinMilli = 31556952000
        val currentTimestamp = System.currentTimeMillis()//지금시간//in milliseconds

        //value for month
        val currentDayOfMonth = Calendar.getInstance().get(Calendar.DAY_OF_MONTH).toLong()
        val lastdayOfMonth = Calendar.getInstance().getActualMaximum(Calendar.DAY_OF_MONTH).toLong()
        val thismonthMillisecond = (lastdayOfMonth * dayinMilli)
        val currentDayMillisecond = (currentDayOfMonth * dayinMilli)

        //value for week
        val calendar = Calendar.getInstance()
        while (calendar.get(Calendar.DAY_OF_WEEK) > calendar.getActualMinimum(Calendar.DAY_OF_WEEK)) {
            calendar.add(Calendar.DATE, -1)

        }
        //setting 00:00:00
        calendar[Calendar.MILLISECOND] = 0
        calendar[Calendar.SECOND] = 0
        calendar[Calendar.MINUTE] = 0
        calendar[Calendar.HOUR_OF_DAY] = 24

        val firstDayOfMonthTimestamp = calendar.timeInMillis
        val timePassedWeek = currentTimestamp - firstDayOfMonthTimestamp


        //shared Preference
        var sharedPreference = getSharedPreferences("CreateProfile", Context.MODE_PRIVATE)
        val userBirth_mili = sharedPreference.getLong("userAge_mili", 1)
        val userDie_mili = sharedPreference.getLong("userDie_mili", 1)
        //get last day of month
        Log.d("Calculate", "userBrith_mili = $userBirth_mili")
        Log.d("Calculate", "userDie_mili = $userDie_mili")


        //LIFE
        val RemainLife = (userDie_mili - currentTimestamp)
        Log.d("Calculate", "RemainLife:$RemainLife")
        val myLifePercent =
            (BigDecimal(100 - RemainLife.toDouble() / (userDie_mili - userBirth_mili) * 100).setScale(
                1,
                RoundingMode.HALF_EVEN
            )).toDouble()
        Log.d("Calculate", "myLifePercent:$myLifePercent")
        //LIFE END


        //YEAR
        val RemainYear = (yearinMilli - currentTimestamp.rem(yearinMilli))
        Log.d("Calculate", "RemainYear:$RemainYear")

        val myYearPercent =
            ((BigDecimal(100 - RemainYear.toDouble() / (yearinMilli) * 100).setScale(
                1,
                RoundingMode.HALF_EVEN
            )).toFloat()).toDouble()
        Log.d("Calculate", "myYearPercent:$myYearPercent")
        //YEAR END


        //MONTH
        val RemainMonth = (thismonthMillisecond - currentDayMillisecond)
        Log.d("Calculate", "RemainMonth:$RemainMonth")

        val myMonthPercent =
            ((BigDecimal(100 - (RemainMonth.toDouble() / thismonthMillisecond * 100)).setScale(
                1,
                RoundingMode.HALF_EVEN
            )).toFloat()).toDouble()
        Log.d("Calculate", "myMonthPercent:$myMonthPercent")
        //MONTH END

        //WEEK
        val RemainWeek = timePassedWeek
        val myWeekPercent =
            ((BigDecimal( timePassedWeek.toDouble() / weekInMilli * 100).setScale(
                1,
                RoundingMode.HALF_EVEN
            )).toFloat()).toDouble()
        //WEKK END

        return TimeData(
            RemainLife,
            myLifePercent,
            RemainYear,
            myYearPercent,
            RemainMonth,
            myMonthPercent,
            RemainWeek,
            myWeekPercent
        )
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }


}

//life reamingin milisecond,
data class TimeData(
    val life: Long,
    val lifePercent: Double,
    val year: Long,
    val yearPercent: Double,
    val month: Long,
    val monthPercent: Double,
    val week: Long,
    val weekPercent: Double
)


fun main(args: Array<String>) {


}



