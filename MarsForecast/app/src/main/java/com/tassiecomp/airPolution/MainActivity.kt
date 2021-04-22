package com.tassiecomp.airPolution

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.tassiecomp.airPolution.retrofit.RetrofitManager

import com.tassiecomp.airPolution.utils.Constants
import com.tassiecomp.airPolution.utils.RESPONSE_STATE
import com.tassiecomp.airPolution.utils.SEARCH_TYPE
import com.tassiecomp.airPolution.utils.onMyTextChanged
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_button_search.*


class MainActivity : AppCompatActivity() {

    //어떤 TYPE에 데이터를 찾을지 설정을해준다. 그리고 DEFUALT값은 PHOSO로 해놓는것.
    private var currentSearchType: SEARCH_TYPE = SEARCH_TYPE.PHOTO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d("TAG", "MainActivity - onCreate() called")

        //라디오 그룹 가져오기
        search_term_radio_group.setOnCheckedChangeListener { _, checkedId ->

            //switch
            when (checkedId) {
                R.id.photo_search_radio_btn -> {
                    Log.d("TAG", "- onCreate() called 메세지 검색 확인")
                    search_term_text_layout.hint = "사진검색"
                    search_term_text_layout.startIconDrawable = resources.getDrawable(R.drawable.ic_photo_library_black_24dp, resources.newTheme())
                }
                R.id.user_search_radio_btn -> {
                    Log.d("TAG","- onCreate() called 사용자검색 버튼 확인!")
                    search_term_text_layout.hint = "사용자검색"
                    search_term_text_layout.startIconDrawable = resources.getDrawable(R.drawable.ic_person_black_24dp, resources.newTheme())
                }
            }

        }

        //텍스트가 변경이 되었을때
        search_term_edit_text.onMyTextChanged {
            if(it.toString().count() >0){
                //입력된글자가 하나라도있으면 검색버튼을 보여준다.
                frame_search_btn.visibility = View.VISIBLE
                search_term_text_layout.helperText = " "
                //스크롤뷰를 올린다.
                main_scrollview.scrollTo(0,200)
            } else {
                frame_search_btn.visibility = View.INVISIBLE
            }

            if (it.toString().count() ==12) {
                Log.d("TAG", "MainActivity - 에러 띄우기 ")
                Toast.makeText(this,"검색어를 12자까지만 입력 가능합니다.", Toast.LENGTH_SHORT).show()
            }
        }

        //버튼 클릭시
        btn_search.setOnClickListener{
            Log.d("TAG", "MainActivity - 검색버튼이 클릭되었다. / currentSearchType : $currentSearchType")

            //검색 api 호출
            RetrofitManager.instance.searchPhotos(searchTerm = search_term_edit_text.text.toString(), completion = {
                responseState, responseDataArrayList ->

                val userSearchInput = search_term_edit_text.text.toString()

                when(responseState) {
                    RESPONSE_STATE.OKAY ->{
                        Log.d("TAG", "API 호출성공: ${responseDataArrayList?.size}")

                        val intent = Intent(this, PhotoCollectionActivity::class.java)

                        val bundle = Bundle()

                        bundle.putSerializable("photo_array_ist", responseDataArrayList)

                        intent.putExtra("array_bundle", bundle)

                        intent.putExtra("search_term", userSearchInput)

                        startActivity(intent)

                    }
                    RESPONSE_STATE.FAIL ->{
                        Log.d("TAG", "API 호출실패: $responseDataArrayList")
                        Toast.makeText(this,"api호출 에러입니다", Toast.LENGTH_SHORT).show()

                    }
                }
             })

            this.handleSearchButtonUi()
        }

    } // oncreate

    private fun handleSearchButtonUi() {
        btn_progress.visibility = View.VISIBLE

        btn_search.text = ""

        Handler().postDelayed({
            btn_progress.visibility = View.INVISIBLE
            btn_search.text = "검색"
        }, 1500)
    }
}