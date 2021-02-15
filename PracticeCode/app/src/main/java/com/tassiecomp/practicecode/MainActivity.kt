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
        //grab text from edit text
        val insertedText = et_text.text.toString()
        //tv_text를 inserted text로 바꿔라
        tv_text.text = insertedText


        //
        val sharedPreference = getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreference.edit()
        //
        editor.apply{
            putString("STRING_KEY",insertedText) //"putString("name of key",value you are going to insert)"
            putBoolean("BOOLEAN_KEY", sw_switch.isChecked)
        }.apply()

        Toast.makeText(this,"Data Saved", Toast.LENGTH_SHORT).show()
    }

    private fun loadData() {
        //getSharedPreferences로 값을 불러올수있음
        val sharedPreference = getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
        val savedString = sharedPreference.getString("STRING_KEY", null) //불러오는 값의 key불러와라라        val savedBoolean = sharedPreference.getBoolean("BOOLEAN_KEY",false)
        val savedBoolean = sharedPreference.getBoolean("BOOLEAN_KEY",false)


        tv_text.text = savedString//TV_TEXT를 saved string값으로 바꿔라
        sw_switch.isChecked = savedBoolean //sw_swiched.ischecked 값을 saved boolean으로 바꿔라

    }
}