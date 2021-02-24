package com.tassiecomp.youtubecopy

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class MainAdapter: RecyclerView.Adapter<>{
    //numberof items
    override fun getItemCount(): Int {
        return 3
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ??? {
        //how do we even create a view

    }

    override fun onBindViewHolder(holder: ???, position: Int) {

    }
}

class CustomViewHolder(v: View): RecyclerView.ViewHolder(v) {

}