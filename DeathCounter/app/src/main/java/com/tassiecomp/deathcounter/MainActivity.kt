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
import com.mikhaellopez.circularprogressbar.CircularProgressBar
import kotlinx.android.synthetic.main.activity_create_profile.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import java.math.BigDecimal
import java.math.RoundingMode
import java.util.*
import kotlin.time.milliseconds


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


            }
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
            setProgressWithAnimation(calculateMilisecond().lifePercent.toFloat(), 1000) // =1s

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


    }

    fun CalculateLife() {
        val ProgressbarLifePercent = calculateMilisecond().lifePercent.toFloat()
        val ProgressbarLifeSecond = calculateMilisecond().life
        val yearLeft = ProgressbarLifeSecond / 31556926000
        Log.d("Progress", "$yearLeft")
        val daysLeft =
            ((ProgressbarLifeSecond.rem(31556926000)).rem(2629746000)) / 86400000
        Log.d("Progress", "$daysLeft")
//
        if (yearLeft.toInt() == 0) {
            if (daysLeft.toInt() == 0) {
                countdownView_title.setText("Are you there?")
            } else {
                countdownView_title.setText("$${daysLeft}Days")
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
        val daysLeft = (ProgressbarYearSecond/ 86400000)
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
        val daysLeft = ProgressbarMonthSecond/86400000

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

    }


    fun calculateMilisecond(): TimeData {
        val yearinMilli = 31556952000
        val lastdayOfMonth = Calendar.getInstance().getActualMaximum(Calendar.DAY_OF_MONTH)
        Log.d("Month1", "this month$lastdayOfMonth")
        val calendar = Calendar.getInstance()
        val monthinMilli =calendar.
        val currentTimestamp = System.currentTimeMillis()//지금시간//in milliseconds
        Log.d("Month1", "this month$monthinMilli")
        //shared Preference
        var sharedPreference = getSharedPreferences("CreateProfile", Context.MODE_PRIVATE)
        val userBirth_mili = sharedPreference.getLong("Birthday_Millis", 1)
        val userDie_mili = sharedPreference.getLong("Die_Millis", 1)
        //get last day of month
        val thismonthMilisecond = lastdayOfMonth * 86400000// 1day is 86400000 milisecond

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


        val RemainMonth = monthinMilli - currentTimestamp.rem(monthinMilli)
        Log.d("Calculate", "RemainMonth:$RemainMonth")

        val myMonthPercent =
            ((BigDecimal(100 - (RemainMonth.toDouble() / thismonthMilisecond * 100)).setScale(
                1,
                RoundingMode.HALF_EVEN
            )).toFloat()).toDouble()
        Log.d("Calculate", "myMonthPercent:$myMonthPercent")
        //MONTH END

        //WEEK
        //WEKK END

        return TimeData(
            RemainLife,
            myLifePercent,
            RemainYear,
            myYearPercent,
            RemainMonth,
            myMonthPercent
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
    val monthPercent: Double
)


fun main(args: Array<String>) {
//    val a = 1
//    val c = a.rem(2)
//    println(c)

    val a = 2000000000000000000
    val b = 70000000000
    val c = BigDecimal(a.toDouble() / b * 100).setScale(2, RoundingMode.HALF_EVEN)
//    val d =  BigDecimal(20.toDouble() / 7).setScale(2, RoundingMode.HALF_EVEN)
//    val num = 3.toDouble() / 6
//    println("$d")
//    println(a%b)
//    println("$num")
    println("percent: $c")

}

