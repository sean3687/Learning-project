package com.tassiecomp.todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tassiecomp.todolist.databinding.ActivityMainBinding
import com.tassiecomp.todolist.databinding.ItemTodoBinding


class MainActivity : AppCompatActivity() {
    //you brought activity main
    private lateinit var binding: ActivityMainBinding

    private var data = arrayListOf<Todo>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //binding setup
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        data.add(Todo("숙제"))
        data.add(Todo("청소"))

        //recycler view with binding
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = TodoAdapter(data)

        binding.addButton.setOnClickListener {
            addTodo()
        }
    }

    //add function
    private fun addTodo() {
        val todo = Todo(binding.editText.text.toString(), false)
        data.add(todo)
        binding.recyclerView.adapter?.notifyDataSetChanged()
    }
}

// Making class for data that you are going to get
data class Todo(val text: String, var isDone: Boolean = false)


class TodoAdapter(private val myDataset: List<Todo>) :
    RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    class TodoViewHolder(val binding: ItemTodoBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    //1.Create holder when there is no existing View holder, so it initially create unused view hodler
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoAdapter.TodoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_todo, parent, false)

        return TodoViewHolder(ItemTodoBinding.bind(view))
    }

    //2. Connecting data with view
    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.binding.todoText.text = myDataset[position].text
    }

    //3. Number of view you are going to make
    override fun getItemCount() = myDataset.size

}

