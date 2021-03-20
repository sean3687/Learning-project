package com.tassiecomp.retrofitpractice

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.tassiecomp.retrofitpractice.retrofit.RetrofitManager
import com.tassiecomp.retrofitpractice.utils.Constants
import com.tassiecomp.retrofitpractice.utils.RESPONSE_STATE
import com.tassiecomp.retrofitpractice.utils.SEARCH_TYPE
import com.tassiecomp.retrofitpractice.utils.onMyTextChanged
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_button_search.*

class MainActivity : AppCompatActivity() {

    private var currentSearchType: SEARCH_TYPE = SEARCH_TYPE.PHOTO

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d(Constants.TAG, "MainActivity - onCreate() called")

        //라디오 그룹 가져오기
        search_term_radio_group.setOnCheckedChangeListener { _, checkedId ->

            when (checkedId) {
                R.id.photo_search_radio_button -> {
                    Log.d("TAG", "사진검색 버튼 클릭!")
                    search_term_text_layout.hint = "사진검색"
                    search_term_text_layout.startIconDrawable =
                        resources.getDrawable(R.drawable.person_icon, resources.newTheme())
                    this.currentSearchType = SEARCH_TYPE.PHOTO
                }
                R.id.user_search_radio_button -> {
                    Log.d("TAG", "사용자 검색 버튼 클릭!")
                    search_term_text_layout.hint = "사용자검색"
                    search_term_text_layout.startIconDrawable =
                        resources.getDrawable(R.drawable.person_icon, resources.newTheme())
                    this.currentSearchType = SEARCH_TYPE.USER
                }
            }
            Log.d(
                "TAG",
                "MainActivity - OnCheckedChange() called / currentSearchType: $currentSearchType"
            )


        }

        //텍스트가  변경이 되었을때
        search_term_edit_text.onMyTextChanged {
            //입력된 글자가 하나라도 있으면
            if (it.toString().count() > 0) {
                //검색버튼을 보여준다.
                frame_search_btn.visibility = View.VISIBLE
                search_term_text_layout.helperText = ""
                //스크롤뷰를 올린다.
                main_scrollview.scrollTo(0, 200)
            } else {
                frame_search_btn.visibility = View.INVISIBLE
            }

            if (it.toString().count() == 12) {
                Log.d("TAG", "MainActivity - 에러 띄우기")
                Toast.makeText(this, "검색어는 12자 까지만 입력 가능합니다", Toast.LENGTH_SHORT).show()

            }
        }
        //검색버튼 클릭시
        btn_search.setOnClickListener {
            Log.d("TAG", "MainActivity - 검색 버튼이 클릭되었다. / currentSearchType: $currentSearchType")

            //검색api 호출
            RetrofitManager.instance.searchPhotos(searchTerm = search_term_edit_text.toString(), completion = {
                    responseState, responseBody ->

                when(responseState){
                    RESPONSE_STATE.OKAY-> {
                        Log.d("TAG","api호출 성공: $responseBody")
                    }
                    RESPONSE_STATE.FAIL ->{
                        Toast.makeText(this,"api 호출 에러", Toast.LENGTH_SHORT).show()
                        Log.d("TAG","api호출 실패: $responseBody")

                    }
                }

            })

            this.handleSearchButtonUi()
        }
    }//onCreate


    private fun handleSearchButtonUi() {
        btn_progress.visibility = View.VISIBLE

        btn_search.text = ""

        Handler().postDelayed({
            btn_progress.visibility = View.INVISIBLE
            btn_search.text = "검색"

        }, 1500)
    }
}

