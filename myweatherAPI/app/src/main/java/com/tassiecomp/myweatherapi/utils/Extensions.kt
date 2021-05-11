package com.tassiecomp.myweatherapi.utils

import android.annotation.SuppressLint
import android.content.SharedPreferences
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import com.google.android.gms.location.FusedLocationProviderClient
import com.tassiecomp.myweatherapi.App
import com.tassiecomp.myweatherapi.Model.Grid


//문자열이 제이슨  형태인지, 재이슨 배열 형태인지
fun String?.isJsonObject():Boolean {
    if(this?.startsWith("{") == true && this.endsWith("}")){
        return true
    } else{
        return false
    }
}

//문자열이 제이슨 배열인지
fun String?.isJsonArray() : Boolean {
    if(this?.startsWith("[") == true && this.endsWith("]")){
        return true
    } else{
        return false
    }
}

//이 extension은 모든EditText들에게 적용을 한다.
fun EditText.onMyTextChanged(completion:(Editable?) -> Unit){
    //this는 edittext를 뜻한다.
    this.addTextChangedListener(object: TextWatcher {

        override fun afterTextChanged(editable: Editable?) {
            completion(editable)
        }
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

        }



    })
}

fun SharedPreferences.Editor.putDouble(key: String, double: Double) =
    putLong(key, java.lang.Double.doubleToRawLongBits(double))

fun SharedPreferences.getDouble(key: String, default: Double) =
    java.lang.Double.longBitsToDouble(getLong(key, java.lang.Double.doubleToRawLongBits(default)))


fun String?.capitalize_first_word(input:String): String {
    val str = input

    val words = str.split(" ")

    var newStr = ""

    words.forEach {
        newStr += it.capitalize() + " "
    }

    return (newStr.trimEnd())
}


open class LocationManager{
    companion object{
        val instance = LocationManager()
        lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    }
    @SuppressLint("MissingPermission")
    fun getLocations(completion: (RESPONSE_STATE, ArrayList<Grid>)->Unit
    ) {

        fusedLocationProviderClient.lastLocation.addOnSuccessListener {
            val parsedGridArray = ArrayList<Grid>()
            if (it == null) {
                Toast.makeText(App.instance, "Error: can't load user location", Toast.LENGTH_SHORT)
                    .show()
                completion(RESPONSE_STATE.FAIL, parsedGridArray)
            } else it.apply {
                val latitude = it.latitude
                val longitude = it.longitude
                Log.d("Location", "RAW LAT&LON :$latitude, $longitude")

                val gridItem = Grid(
                    latitude = latitude,
                    longitude = longitude
                )

                parsedGridArray.add(gridItem)


                //sharedpreference
//                var sharedPreference =
//                    getSharedPreferences("gridLocation", Context.MODE_PRIVATE)
//                val editor: SharedPreferences.Editor = sharedPreference.edit()
//                editor.putDouble("lat", latitude)
//                editor.putDouble("lon", longitude)
//                editor.apply()


            }
            completion(RESPONSE_STATE.OKAY, parsedGridArray)

        }


    }
}