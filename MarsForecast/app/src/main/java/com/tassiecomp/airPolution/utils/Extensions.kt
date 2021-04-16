package com.tassiecomp.airPolution.utils

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*

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