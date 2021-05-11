package com.tassiecomp.myweatherapi.RecyclerView

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tassiecomp.myweatherapi.Model.DailyWeather
import com.tassiecomp.myweatherapi.R

class DailyRecyclerViewAdapter : RecyclerView.Adapter<DailyItemViewHolder>() {


    private var dailyWeatherList = ArrayList<DailyWeather>()

    //IMPLEMENT MEMBERS
    //view holder와 layout 연결
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DailyItemViewHolder {
        //photoItem viewholder를 만든다.
        val DailyItemViewHolder = DailyItemViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.layout_dailyweather_item, parent, false) //반복될 grid형 안에 들어갈 view를 불러온다.
        )

        return DailyItemViewHolder //PHOTOitemViewholder를 반환해야한다.
    }

    //보여줄 목록의 갯수
    override fun getItemCount(): Int {
        return this.dailyWeatherList.size
        Log.d("Numbernumber","${dailyWeatherList.size}")
    }

    //뷰가 묶였을때 데이터를 뷰홀더에 넘겨준다.
    override fun onBindViewHolder(holder: DailyItemViewHolder, position: Int) {
        holder.bindWithView(this.dailyWeatherList[position])
    }

    //외부에서 어뎁터에 데이터 배열을 넣어준다.
    fun submitList(dailyWeatherList: ArrayList<DailyWeather>){
        this.dailyWeatherList = dailyWeatherList
    }

}