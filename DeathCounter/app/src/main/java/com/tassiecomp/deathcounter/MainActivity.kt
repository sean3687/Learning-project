package com.tassiecomp.deathcounter

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.provider.Settings.Global.getString
import android.provider.Settings.Secure.getString
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_create_profile.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //load data
        val sharedPreference = getSharedPreferences("CreateProfile", Context.MODE_PRIVATE)
        val savedUserName = sharedPreference.getString("userName", null)
        val savedUserAge = sharedPreference.getInt("userAge", 0)
        val savedDieAge = sharedPreference.getInt("userDie", 0)
        Log.d("TAG", "MainActivity: SavedUserName: $savedUserName, SavedUserAge: $savedUserAge")


        //checking if registered

        when {
            savedUserName != "null" -> {
                Log.d("TAG", "Empty")
                val intent = Intent(this@MainActivity, CreateProfile::class.java)
                startActivity(intent)

            }
            else -> {
                Log.d("TAG", "there is value")
                Log.d("TAG", "saved value: $savedUserName")

            } //nothing happen
        }




    }


}





