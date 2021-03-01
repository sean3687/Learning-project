package com.tassiecomp.gridlayout.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.tassiecomp.gridlayout.MainActivity
import com.tassiecomp.gridlayout.R
import com.tassiecomp.gridlayout.model.AlphaChar


//grid layout에서도 똑같이 recycler view를 만든다.
// alpha adapter들에 context와 alphaChar클래스를 만든 리스트를 가져온다.
class AlphaAdapters(var context: Context, var arrayList: ArrayList<AlphaChar>) :
    RecyclerView.Adapter<AlphaAdapters.ItemHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val itemHolder = LayoutInflater.from(parent.context)
            .inflate(R.layout.grid_layout_list_item, parent, false)
        return ItemHolder(itemHolder)
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }


    class ItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var icons = itemView.findViewById<ImageView>(R.id.icons_image)
        var alphas = itemView.findViewById<TextView>(R.id.alpha_text_view)
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        var alphaChar: AlphaChar = arrayList.get(position)

        holder.icons.setImageResource(alphaChar.iconsChar!!)
        holder.alphas.text = alphaChar.alphaChar

        holder.alphas.setOnClickListener {
        }
    }

}