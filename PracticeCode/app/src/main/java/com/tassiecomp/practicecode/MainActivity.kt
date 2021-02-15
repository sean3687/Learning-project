package com.tassiecomp.practicecode

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadData()

        bt_button.setOnClickListener{
            saveData()
        }
    }

    private fun saveData(){
        val insertedText = et_text.text.toString()
        tv_text.text = insertedText

        val sharedPreference = getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreference.edit()
        editor.apply(){
            putString("STRING_KEY",insertedText)
            putBoolean("BOOLEAN_KEY", sw_switch.isChecked)
        }.apply()

        Toast.makeText(this,"Data Saved", Toast.LENGTH_SHORT).show()
    }

    private fun loadData() {
        val sharedPreference = getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
        val savedString = sharedPreference.getString("STRING_KEY", null)
        val savedBoolean = sharedPreference.getBoolean("BOOLEAN_KEY",false)

        tv_text.text = savedString
        sw_switch.isChecked = savedBoolean

    }
}