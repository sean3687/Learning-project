package com.example.recyclerviewpractice

import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.layout_recycler_item.view.*

//이아래는 커스텀 뷰홀더이다.
//myViewholder의 생성자에 view를 넣어준다. view는 layout_recycleritem을 말하는것

class MyViewholder(itemView: View,
                   recylerviewInterface: MyRecyclerviewInterface):
                    RecyclerView.ViewHolder(itemView),
                    View.OnClickListener
{
    val TAG: String = "로그"
    private val usernameTextView = itemView.user_name_txt
    private val profileImageView = itemView.profile_img
    private var myRecyclerviewInterface: MyRecyclerviewInterface? = null
    //기본생성자
    init {
        Log.d(TAG, "MyViewHolder - init() called")
        itemView.setOnClickListener(this)
        this.myRecyclerviewInterface = recylerviewInterface
    }

    //만든 viewholder 랑 데이터랑 묶어줘야한다. 즉뷰와 data를 묶어준다.
    fun bind(myModel: MyModel){
        Log.d(TAG, "MyViewHolder - bind() called")
        //text를 가져오고
        usernameTextView.text = myModel.name
        //이미지를 glide를 통해 가져오는 작업
        Glide

            .with(App.instance) //여기에 context가 와야한다.
            .load(myModel.profileImage) //우리가 적었던 url
            .centerCrop()
            .placeholder(R.drawable.ic_launcher_background)//런처 이미지
            .into(profileImageView)
    }

    override fun onClick(v: View?) {
        Log.d(TAG,"MyViewHolder - onClick() called")
        this.myRecyclerviewInterface?.onItemClicked()
    }
}
