package com.tassiecomp.myweatherapi.RecyclerView

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.tassiecomp.myweatherapi.Model.Weather
import kotlinx.android.synthetic.main.layout_subweather_item.view.*

class PhotoItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    val weatherTitle = arrayList()
    private val weatherTitle = itemView.sub_weather_title // photo image는 layout_photo_item파일에서 id들이다.
    private val weatherIcon = itemView.sub_weather_icon
    private val weatherValue = itemView.sub_weather_value

    fun bindWithView(weatherItem: Weather) {
        Log.d("TAG","PhotoItemViewHolder - bindwithView() called")

        weatherTitle.text = weatherItem.createdAt

        photoLikesCountText.text = photoItem.likesCount.toString()

        //이미지를 설정한다.
        Glide.with(App.instance) //전역으로 context를 넣어준다.
            .load(photoItem.thumbnail)
            .placeholder(R.drawable.ic_baseline_insert_photo_24)
            .into(photoImageView)


    }


}