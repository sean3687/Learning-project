package com.tassiecomp.deathcounter

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_create_profile.*
import java.io.FileNotFoundException
import java.io.FileOutputStream

class CreateProfile : AppCompatActivity() {




    //setting up internal data storage


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_profile)

        Save_create.setOnClickListener {
            performRegister()
        }



    }


    //function extension for toast.makeText
    fun Context.showToast(text: CharSequence, duration: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(this, text, duration).show()
    }

    fun performRegister() {
        //bring text in edittext box and make it to string and assign as each variable
        val userName = username_create.text.toString()
        val userAge = userAge_create.text.toString()
        val userDie = dieDate_create.text.toString()

        //assigning text file name
        val userdatafile: String = "userDatafile.txt"
        //setting up internal data storage
        val fileOutputStream: FileOutputStream

        for(i in 0..1)
        //no more than 3 digit userAge
        if(userAge.length > 3 || userDie.length > 3){
            showToast("userAge no longer than 3 digit")
                break

            }else if(userAge.length > userDie.length){

                showToast("your current age is smaller than death age")
                break

            }else{
            try {
                //assigning textfile name
                fileOutputStream = openFileOutput(userdatafile, Context.MODE_PRIVATE)
                //assigning what to put on this data file
                //we write username inside
                fileOutputStream.write(userName.toByteArray())
                fileOutputStream.write(userAge.toByteArray())
                fileOutputStream.write(userDie.toByteArray())

                //just in case there's no data we add exception
            } catch (e: FileNotFoundException) {
                e.printStackTrace()
            } catch (e: Exception) {
                e.printStackTrace()
            }
                showToast("Saved")

            }










    }
}