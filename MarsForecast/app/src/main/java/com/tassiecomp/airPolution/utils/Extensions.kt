package com.tassiecomp.airPolution.utils

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*

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