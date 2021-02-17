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

        //checking if want to register
        val openCreateProfile = Intent(this@MainActivity, CreateProfile::class.java) //인텐트(요청)를 만든상태


        when {
            userNameValue() -> {
                Log.d("TAG", "Empty")
            }
            else -> {
            } //nothing happen
        }

    }


    private fun loadData() {
        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        val savedUserName = SharedPreferences.getString("userName", null)
        val savedUserAge = SharedPreferences.getInt("userAge", null)

    }

}





