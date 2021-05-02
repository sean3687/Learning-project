package com.tassiecomp.myweatherapi.utils

import android.content.SharedPreferences
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText


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