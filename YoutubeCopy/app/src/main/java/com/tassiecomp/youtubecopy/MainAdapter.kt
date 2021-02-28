package com.tassiecomp.youtubecopy

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.video_row.view.*


//then how are we going to use this data?
//1. we have to use this data in adapter bc that's where we match data and ui
//2. so on class Mainadapter(val home:Feed) // add home:feed as value
//3. we don't need any other value as channel and video bc in the homefeed, it include viedo and channel, homefeed is head of it

class MainAdapter(val homeFeed: Models.HomeFeed) : RecyclerView.Adapter<CustomViewHolder>() {

    val videoTitles = listOf<String>("First title", "Second", "3rd")


    //numberOfItems
    override fun getItemCount(): Int {
        return homeFeed.videos.count()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(parent?.context)
        val cellForRow = layoutInflater.inflate(R.layout.video_row, parent, false)
        return CustomViewHolder(cellForRow)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
//        val videoTitle = videoTitles.get(position)
        // homefeed에서 videos들을 가져온다. (position) 순서대로
        val video = homeFeed.videos.get(position)
        holder?.view?.textView_video_title.text = video.name //video/name 경로를 적어준것이다.
        holder.view.textView_channel_name.text = video.channel.name + " • " + "20K Views\n4 days ago" //video/channel/name안에있는값을 가져온다.

        //bring image with picasso
        //inserting thumbnai l
        val thumbnailImageView = holder?.view?.imageView_video_thumbnail
        Picasso.with(holder?.view.context).load(video.imageUrl).into(thumbnailImageView)
        //inserting profile image

        val channelProfileImageView = holder.view.imageView_channel_profile
        Picasso.with(holder?.view.context).load(video.channel.profileImageUrl).into(channelProfileImageView)

    }
}

class CustomViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

}