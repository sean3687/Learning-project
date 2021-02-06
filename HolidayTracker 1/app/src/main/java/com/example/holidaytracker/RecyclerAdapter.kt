package com.example.holidaytracker

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerAdapter(private var daysleft: List<Int>, private var date: List<String>, private var holidayname: List<String>, private var icon: MutableList<Any>) :
    RecyclerView.Adapter<RecyclerAdapter.ViewHolder>(){
        inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){


            val daySub: TextView = itemView.findViewById(R.id.daysleft_subtitle_row)
            val dateSub: TextView = itemView.findViewById(R.id.dateHoliday_row)
            val nameSub: TextView = itemView.findViewById(R.id.holidaysName_row)
            val iconSub: ImageView = itemView.findViewById(R.id.icon_img)

            init{
                itemView.setOnClickListener{v: View->
                    val position:Int = adapterPosition

                }
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.holidays_row,parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return daysleft.size
    }



    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.daySub.text = daysleft[position].toString()
        holder.dateSub.text = date[position].toString()
        holder.nameSub.text = holidayname[position].toString()
        holder.iconSub.setImageDrawable(icon[position])
    }
}

private fun ImageView.setImageDrawable(any: Any) {

}
