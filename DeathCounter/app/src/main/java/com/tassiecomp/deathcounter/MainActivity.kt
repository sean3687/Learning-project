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
import java.lang.Math.round
import java.math.BigDecimal
import java.math.RoundingMode
import java.util.*
import kotlin.time.minutes


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


        //load data
        val sharedPreference = getSharedPreferences("CreateProfile", Context.MODE_PRIVATE)
        val savedUserName = sharedPreference.getString("userName", null)
        val savedUserAge = sharedPreference.getInt("userAge", 0)
        val savedDieAge = sharedPreference.getInt("userDie", 0)
        Log.d("TAG", "MainActivity: SavedUserName: $savedUserName, SavedUserAge: $savedUserAge")


        //checking if registered

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

            } //nothing happen
        }


        //circular progress bar
        //
        val circularProgressBar = findViewById<CircularProgressBar>(R.id.circularProgressBar)
        circularProgressBar.apply {
            // Set Progress (setting up initial progress)
            progress = 0f
            // or with animation, show end progress with animation
            setProgressWithAnimation(65f, 1000) // =1s

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

        my_month_left.setOnClickListener{
            CalculateMonth()
        }



    }

    fun CalculateLife() {
        val ProgressbarLifePercent = calculateMilisecond().lifePercent.toFloat()
        val ProgressbarLifeSecond = calculateMilisecond().life
        val yearLeft = ProgressbarLifeSecond/31556926000 //1년을 나눈 몫 //몇년 남았는지
        val daysLeft = ((ProgressbarLifeSecond.rem(31556926000)).rem(2629746000))/86400000 //나머지에 1달을 나눈다.// 몇달남았는지//몇일 남았는지//몇일로 보이기
        val hoursLeft =(((ProgressbarLifeSecond.rem(31556926000)).rem(2629746000)).rem(86400000))/3600000
        when {
            yearLeft.toInt() == 0 -> {
                if (hoursLeft.toInt() ==0){
                    countdownView_title.text = "1 Hour remain"
                } else {
                    countdownView_title.text = "$daysLeft"
                }
            }

        }
        circularProgressBar.setProgressWithAnimation(ProgressbarLifePercent, 1000)


    }

    fun CalculateYear() {
        val ProgressbarYearPercent = calculateMilisecond().yearPercent.toFloat()
        val ProgressbarYearSecond = calculateMilisecond().year.toFloat()

        circularProgressBar.setProgressWithAnimation(ProgressbarYearPercent, 1000)
        countdownView_title.setText("${ProgressbarYearSecond}")
        Percent_title.text = "${ProgressbarYearPercent}%"
        statement_title.text= "Year end in"

    }

    fun CalculateMonth() {
        val ProgressbarMonthPercent = calculateMilisecond().monthPercent.toFloat()
        val ProgressbarMonthSecond = calculateMilisecond().month.toFloat()

        circularProgressBar.setProgressWithAnimation(ProgressbarMonthPercent, 1000)
        Percent_title.text = "${ProgressbarMonthPercent}%"
        statement_title.text= "This Month end in"
    }

    fun CalculateWeek(){

    }d


    fun calculateMilisecond(): TimeData {
        val sharedPreference = getSharedPreferences("CreateProfile", Context.MODE_PRIVATE)
        val savedDieDate = sharedPreference.getInt("DieDate", 0)
        val savedBirthDate = sharedPreference.getInt("BirthDate",0)//in milliseconds
        val currentTimestamp = System.currentTimeMillis()//지금시간//in milliseconds
        val lastdayOfMonth = Calendar.getInstance().getActualMaximum(Calendar.DAY_OF_MONTH).toLong()//get last day of month
        val thismonthMilisecond = lastdayOfMonth * 86400000// 1day is 86400000 milisecond
        val userAge = currentTimestamp - savedBirthDate
        val userDieAge = (savedDieDate - savedBirthDate).toLong()
        Log.d("save", "userAge:$userAge")
        Log.d("save", "userDieAge:$userDieAge")

        val remain = currentTimestamp.rem(31556952000)
        Log.d("Calculate", "remain:$remain")

        //this year end in this milisecond
        val RemainYear = (31556952000 - currentTimestamp.rem(31556952000))
        Log.d("Calculate", "RemainYear:$RemainYear")

        val myYearPercent =
            ((BigDecimal(100 -RemainYear.toDouble() / (31556952000) * 100).setScale(
                1,
                RoundingMode.HALF_EVEN
            )).toFloat()).toDouble()
        Log.d("Calculate", "myYearPercent:$myYearPercent")

        //remianing milisecond
        val RemainAge = savedDieDate - currentTimestamp
        Log.d("Calculate", "RemainAge:$RemainAge")

        // 30% done in my life
        val myLifePercent =
            (BigDecimal(1 / 3 * 100).setScale(
                1,
                RoundingMode.HALF_EVEN
            )).toDouble()
        Log.d("Calculate", "myLifePercent:$myLifePercent")

        //3000second left until this month end
        val RemainMonth = 2629746000 - currentTimestamp.rem( 2629746000 ) //time right now/ this month will left out days from beginning
        Log.d("Calculate", "RemainMonth:$RemainMonth")

        val myMonthPercent =  ((BigDecimal(100-(RemainMonth.toDouble() / thismonthMilisecond * 100)).setScale(
            1,
            RoundingMode.HALF_EVEN
        )).toFloat()).toDouble()

        Log.d("Calculate", "myMonthPercent:$myMonthPercent")

        return TimeData(RemainAge, myLifePercent, RemainYear, myYearPercent, RemainMonth, myMonthPercent)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }


}

//life reamingin milisecond,
data class TimeData(val life: Long, val lifePercent: Double, val year: Long, val yearPercent: Double, val month:Long, val monthPercent:Double)





fun main(args:Array<String>) {
//    val a = 1
//    val c = a.rem(2)
//    println(c)

    val a = 2000000000000000000
    val b = 70000000000
    val c: BigDecimal = BigDecimal(a.toDouble() / b *100).setScale(2, RoundingMode.HALF_EVEN)
//    val d =  BigDecimal(20.toDouble() / 7).setScale(2, RoundingMode.HALF_EVEN)
//    val num = 3.toDouble() / 6
//    println("$d")
//    println(a%b)
//    println("$num")
    println("percent: $c")
}