package com.example.recyclerviewpractice

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class MyRecyclerAdpater(myRecyclerviewInterface: MyRecyclerviewInterface): RecyclerView.Adapter<MyViewholder>() {

    val TAG: String = "로그"

    private var modelList = ArrayList<MyModel>()

    private var myRecyclerviewInterface :MyRecyclerviewInterface?= null

    //생성자
    init{
        this.myRecyclerviewInterface = myRecyclerviewInterface
    }


    // viewholder가 생성됬을때
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewholder {
        return MyViewholder(LayoutInflater.from(parent.context).inflate(R.layout.layout_recycler_item,parent, false),this.myRecyclerviewInterface!!)
    }
    // 목록의 수
    override fun getItemCount(): Int {
        return this.modelList.size
    }
    // 뷰와 뷰홀더가 묶였을때
    override fun onBindViewHolder(holder: MyViewholder, position: Int) {
        Log.d(TAG, "MyRecyclerAdapter - onBindViewHolder() called / position: $position")
        holder.bind(this.modelList[position])

        //클릭 리스너 설정
        holder.itemView.setOnClickListener{
            Toast.makeText(App.instance,"클릭됨! ${this.modelList[position].name}", Toast.LENGTH_SHORT).show()
        }
    }

    //외부에서 데이터 넘기기
    fun submitList(modelList: ArrayList<MyModel>){
        this.modelList = modelList
    }
}