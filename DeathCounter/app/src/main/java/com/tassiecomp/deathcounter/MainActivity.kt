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
import java.math.BigDecimal
import java.math.RoundingMode


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
        intent.putExtra("from",1)

        navView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.profileIcon ->
                startActivity(intent)


                R.id.settingIcon -> Toast.makeText(
                    applicationContext,
                    "Clicked setting", Toast.LENGTH_SHORT
                ).show()
                R.id.helpIcon-> Toast.makeText(
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
                intent.putExtra("from",0)
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

        //stopped at bring percentage from cal culate milisecond function
        my_life_left.setOnClickListener{
            val ProgressbarLifePercent = calculateMilisecond().lifePercent
            val ProgressbarLifeSecond = calculateMilisecond().life

            circularProgressBar.setProgressWithAnimation(ProgressbarLifePercent, 1000)
            countdownView_title.start(ProgressbarLifeSecond)
            Percent_title.text = "${ProgressbarLifePercent}%"


        }


    }


    fun calculateMilisecond(): TimeData {
        val sharedPreference = getSharedPreferences("CreateProfile", Context.MODE_PRIVATE)
        val savedUserAge = sharedPreference.getInt("userAge", 0)
        val savedDieAge = sharedPreference.getInt("userDie", 0)
        val currentTimestamp = System.currentTimeMillis()//지금시간
        Log.d("Calculate", "currentTimestamp:$currentTimestamp")

        val remain = currentTimestamp.rem(31556952000)
        Log.d("Calculate", "remain:$remain")

        //this year end in this milisecond
        val RemainYear = (31556952000-currentTimestamp.rem(31556952000))
        Log.d("Calculate", "UntilYearEnd:$RemainYear")

        val myYearPercent =(100 - (BigDecimal(RemainYear.toDouble() / (31556952000)*100).setScale(2, RoundingMode.HALF_EVEN)).toInt()).toFloat()
        Log.d("Calculate", "myYearPercent:$myYearPercent")

        //remianing milisecond
        val RemainAge = RemainYear +(savedDieAge - savedUserAge)*31556952000
        Log.d("Calculate", "dieAge:$RemainAge")

        //i m 30% done in my life
        val myLifePercent = (100 - (BigDecimal(RemainAge.toDouble() / (savedDieAge*31556952000)*100).setScale(2, RoundingMode.HALF_EVEN)).toInt()).toFloat()
//            (dieAge/(savedDieAge*31556952000)*100)
        Log.d("Calculate", "$myLifePercent")

        return TimeData(RemainAge,myLifePercent,RemainYear,myYearPercent)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }


}
//life reamingin milisecond,
data class TimeData(val life: Long, val lifePercent: Float, val year: Long, val yearPercent: Float)


//fun main(args:Array<String>){
//    val a = 20
//    val b = 7
//    val c = BigDecimal((a%b)/20).setScale(2, RoundingMode.HALF_EVEN)
//    val d =  BigDecimal(20.toDouble() / 7).setScale(2, RoundingMode.HALF_EVEN)
//    val num = 3.toDouble() / 6
//    println("$d")
//    println(a%b)
//    println("$num")
//    println("percent: $c")
//}



