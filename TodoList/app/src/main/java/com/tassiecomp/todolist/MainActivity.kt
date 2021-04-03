package com.tassiecomp.todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tassiecomp.todolist.databinding.ActivityMainBinding
import com.tassiecomp.todolist.databinding.ItemTodoBinding

private lateinit var binding: ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var data = arrayListOf<Todo>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        val view = binding.root
        setContentView(view)

        data.add(Todo("숙제",false))
        data.add(Todo("청소", false))
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = TodoAdapter(data)

        binding.addButton.setOnClickListener {
            addTodo()

        }

    }

    private fun addTodo(){
        val todo = Todo(binding.editText.text.toString())
        data.add(todo)
        binding.recyclerView.
    }
}

data class Todo(val text: String, var isDone: Boolean = false)


class TodoAdapter(private val myDataset: List<Todo>) :
    RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {


    class TodoViewHolder(val binding: ItemTodoBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): TodoViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_todo, viewGroup, false)
        return TodoViewHolder(ItemTodoBinding.bind(view))
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.binding.todoText.text = myDataset[position].text
    }

    override fun getItemCount() = myDataset.size

}
