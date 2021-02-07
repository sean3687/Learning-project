package com.example.holidaytracker

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerAdapter(
    private var daysleft: List<String>,
    private var date: List<String>,
    private var holidayname: List<String>,
    private var icon: MutableList<Any>
) :
    RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        val daySub: TextView = itemView.findViewById(R.id.daysleft_subtitle_row)
        val dateSub: TextView = itemView.findViewById(R.id.dateHoliday_row)
        val nameSub: TextView = itemView.findViewById(R.id.holidaysName_row)
        val iconSub: ImageView = itemView.findViewById(R.id.icon_img)

        init {
            itemView.setOnClickListener { v: View ->
                val position: Int = adapterPosition

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.holidays_row, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return daysleft.size
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.daySub.text = daysleft[position].toString()
        holder.dateSub.text = date[position].toString()
        holder.nameSub.text = holidayname[position].toString()
        //이미지를 불러올때 .setImageResource를 써주고 icon[position]포지션은 0부터 시작된다.
        //icon은 뭘 의미하나?
        //1. 맨위에 있는 recycler adapter 에서 var list를 만들어줬다. 여기안에있는 컨텐츠는 어디서 가져오는것인가.
        //2. main activity 에서 rrecycler adapter에서 가져온다. recycler_sub_main.adapter = RecyclerAdapter(displayDaysLeft_sub, displayListName, displayMDformat_sub, displayIcon_sub)
        //3. 가장 마지막 위치였던, displayicon_sub를 가져온다.
        //4. displayicon_sub에서 가져온 것을 recycler adapter에서 새로 만든 var icon list에 넣어준다. 그 리스트들은
        //5. 이아래 holder로 들어가서 icon list에 position0 부터 하나하나 넣어준다.
        holder.iconSub.setImageResource(icon[position] as Int)
    }
}


