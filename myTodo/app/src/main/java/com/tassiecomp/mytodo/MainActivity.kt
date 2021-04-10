package com.tassiecomp.mytodo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    class TodoAdapter(private val dataSet: Array<String>) : //todoadapter에 구성은 dataset이다
        RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() { //이부분은 항상 고정

        class TodoViewHolder(view: View) : RecyclerView.ViewHolder(view) { //리사이클러뷰의 뷰홀더를 상속받은 todoView holder
            val textView: TextView

            init {
                textView = view.findViewById(R.id.textView)
            }
        }

        override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): TodoViewHolder {
            val view = LayoutInflater.from(viewGroup.context)
                .inflate(R.layout. , viewGroup, false)

            return TodoViewHolder(view)
        }

        override fun onBindViewHolder(viewHolder: TodoViewHolder, position: Int) {
            viewHolder.textView.text = dataSet[position]
        }

        override fun getItemCount() = dataSet.size

    }
}