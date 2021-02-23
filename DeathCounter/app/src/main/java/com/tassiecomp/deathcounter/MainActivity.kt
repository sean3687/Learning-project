package com.tassiecomp.deathcounter

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import com.tassiecomp.deathcounter.Nav.ProfileNav
import kotlinx.android.synthetic.main.activity_create_profile.*
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {


    lateinit var toggle: ActionBarDrawerToggle


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toggle = ActionBarDrawerToggle(this, drawerlayout, R.string.open, R.string.close)
        drawerlayout.addDrawerListener(toggle)
        toggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val IntentProfile = Intent(this@MainActivity, ProfileNav::class.java)

        navView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.profileIcon ->
                startActivity(IntentProfile)


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
                startActivity(intent)

            }
            else -> {
                Log.d("TAG", "there is value")
                Log.d("TAG", "saved value: $savedUserName")

            } //nothing happen
        }


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }


}




