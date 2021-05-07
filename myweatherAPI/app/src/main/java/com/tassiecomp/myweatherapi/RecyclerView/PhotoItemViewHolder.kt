package com.tassiecomp.myweatherapi.RecyclerView

import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tassiecomp.myweatherapi.App
import com.tassiecomp.myweatherapi.Model.Weather
import com.tassiecomp.myweatherapi.R
import kotlinx.android.synthetic.main.layout_subweather_item.view.*

//class PhotoItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
//
//    private val weatherTitle = itemView.sub_feelslike_title // photo image는 layout_photo_item파일에서 id들이다.
//    private val weatherIcon = itemView.sub_feelslike_icon
//    private val weatherValue = itemView.sub_feelslike_value
//
//    fun bindWithView(weatherItem: Weather) {
//        Log.d("TAG","PhotoItemViewHolder - bindwithView() called")
//
//        weatherTitle.text = weatherItem.
//
//        weatherIcon.text = weatherItem.
//
//        weatherValue.text = weatherItem.
//
//        //이미지를 설정한다.
//        Glide.with(App.instance) //전역으로 context를 넣어준다.
//            .load(photoItem.thumbnail)
//            .placeholder(R.drawable.ic_baseline_insert_photo_24)
//            .into(photoImageView)
//
//
//    }
//
//
//}