package com.tassiecomp.myweatherapi.RecyclerView

import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tassiecomp.myweatherapi.App
import com.tassiecomp.myweatherapi.Model.DailyWeather
import com.tassiecomp.myweatherapi.Model.Weather
import com.tassiecomp.myweatherapi.R
import kotlinx.android.synthetic.main.layout_dailyweather_item.view.*

class DailyItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    private val dateday = itemView.date_day // photo image는 layout_photo_item파일에서 id들이다.
    private val pop = itemView.popvalue
    private val icon = itemView.dailyicon
    private val minmax_temp = itemView.minmax_dailytemp

    fun bindWithView(dailyItem: DailyWeather) {
        Log.d("TAG","PhotoItemViewHolder - bindwithView() called")

        dateday.text = dailyItem.date

        pop.text = "${dailyItem.pop.toString()}%"

        minmax_temp.text = "${dailyItem.tempMax?.toInt()}°/${dailyItem.tempMin?.toInt()}°"




        val iconID = dailyItem.icon.toString()
        val iconurl = "https://openweathermap.org/img/wn/$iconID@2x.png"
        //이미지를 설정한다.
        Glide.with(App.instance) //전역으로 context를 넣어준다.
            .load(iconurl)
            .placeholder(R.drawable.ic_baseline_cloud_queue_24)
            .into(icon)


    }


}