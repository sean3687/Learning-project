package com.example.rockscissorpaper

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_list_view.*

class ListViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_view)

        //자동차 리스트
        val carList = ArrayList<CarForList>()
        for (i in 0 until 10) {
            carList.add(CarForList("" + i + " 번째 자동차", "" + i + "순위 엔진"))
        }

        val adapter = ListViewAdapter(carList, LayoutInflater.from(this@ListViewActivity))
        listView.adapter = adapter
        //리스트뷰에 리스너를 장착하는방법
        listView.setOnItemClickListener{parent, view, postion, id -> //넘어오는 정보들
            //리스너를 눌렀을때 토스트 메세지를 띄울것이다.
            adapter.getItem(postion)


        }

    }
}

//이 어답터가 화면에 총 몇개를 보여줄건지 조정을한다.
class ListViewAdapter(
    val carForList : ArrayList<CarForList>,
    val layoutInflater: LayoutInflater
): BaseAdapter() {



    override fun getCount(): Int {
        //그리고자 하는 아이템 리스트의 전체 갯수
        return carForList.size
    }

    override fun getItem(position: Int): Any {
        //그리고자 하는 아이템 리스트중 하나, 하나를 선택하는 기준은 position에 해당하는 아이템
        return carForList.get(position)
    }

    override fun getItemId(position: Int): Long {
        //해당포지션에 위치해있는 아이템뷰의 아이디 설정
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
    //뷰를 그리는 부분
        val view = layoutInflater.inflate(R.layout.item_view, null)
        val carNameTextView = view.findViewById<TextView>(R.id.car_name)
        val carEngineTextView = view.findViewById<TextView>(R.id.car_engine)

        carNameTextView.setText(carForList.get(position).name)
        carEngineTextView.setText(carForList.get(position).engine)

        return view
    }
}