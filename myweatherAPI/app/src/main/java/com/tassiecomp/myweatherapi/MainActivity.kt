package com.tassiecomp.myweatherapi

import android.content.Context
import android.content.Intent
import android.location.Address
import android.location.Criteria
import android.location.Geocoder
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.tassiecomp.myweatherapi.api.RetrofitManager
import com.tassiecomp.myweatherapi.utils.RESPONSE_STATE
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        load_button.setOnClickListener {
            Log.d("TAG", "MainActivity - 검색버튼이 클릭되었다.")

            RetrofitManager.instance.getData(
                searchTerm = editText_term.text.toString(),
                completion = { responseState, responseBody ->

                    val userSearchInput = editText_term.text.toString()

                    when (responseState) {
                        RESPONSE_STATE.OKAY -> {
                            Log.d("TAG", "API 호출성공: $responseBody")

                            val intent = Intent(this, weatherDetail::class.java)

                            val bundle = Bundle()


                        }
                        RESPONSE_STATE.FAIL -> {
                            Log.d("TAG", "API 호출 에러 : $responseBody")
                        }
                    }
                })



        }


    }

}