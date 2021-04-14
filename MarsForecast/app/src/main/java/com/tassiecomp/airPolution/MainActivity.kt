package com.tassiecomp.airPolution

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

import com.tassiecomp.airPolution.utils.Constants
import com.tassiecomp.airPolution.utils.SEARCH_TYPE
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    //어떤 TYPE에 데이터를 찾을지 설정을해준다. 그리고 DEFUALT값은 PHOSO로 해놓는것.
    private var currentSearchType: SEARCH_TYPE = SEARCH_TYPE.PHOTO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d(Constants.TAG, "MainActivity - onCreate() called")

        //라디오 그룹 가져오기
        search_term_radio_group.setOnClickListener { _, checkedId ->

            //switch
            when (checkedId) {
                R.id.photo_search_radio_btn -> {
                    Log.d(TAG, "메세지 검색 확인")
                    search_term_text_layout.hint = "사진검색"
                    search_term_text_layout.startIconDrawable =
                        resource.getDrawable(R.drawable.ic_photo_library_black_24dp, resources)
                }
            }
        }
    }
}