package com.tassiecomp.todolist

import android.graphics.Paint
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tassiecomp.todolist.databinding.ActivityMainBinding
import com.tassiecomp.todolist.databinding.ItemTodoBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        val view = binding.root
        setContentView(view)


        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = TodoAdapter(
                viewModel.data,
                onClickDeleteIcon = {
                    viewModel.deleteTodo(it)
                    binding.recyclerView.adapter?.notifyDataSetChanged()
                }, onClickItem = {
                    viewModel.toggleTodo(it)
                    binding.recyclerView.adapter?.notifyDataSetChanged()
                }
            )
        }

        binding.addButton.setOnClickListener {
            val todo = Todo(binding.editText.text.toString())
            viewModel.addTodo(todo)
            binding.recyclerView.adapter?.notifyDataSetChanged()

        }


    }

}

data class Todo(
    val text: String,
    var isDone: Boolean = false
)


class TodoAdapter(
    private val myDataset: List<Todo>,
    val onClickDeleteIcon: (todo: Todo) -> Unit,
    val onClickItem: (todo: Todo) -> Unit

) :
    RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {


    class TodoViewHolder(val binding: ItemTodoBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): TodoViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_todo, viewGroup, false)
        return TodoViewHolder(ItemTodoBinding.bind(view))
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val todo = myDataset[position]
        holder.binding.todoText.text = myDataset[position].text

        if (todo.isDone) {
//            holder.binding.todoText.paintFlags = holder.binding.todoText.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
            //위와 같은 코드지만 더 간편하게 쓸수있다.
            holder.binding.todoText.apply {
                paintFlags = paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                setTypeface(null, Typeface.ITALIC)
            }
        } else {
            holder.binding.todoText.apply {
                paintFlags = 0
                setTypeface(null, Typeface.NORMAL)
            }
        }


        holder.binding.deleteImageView.setOnClickListener {
            onClickDeleteIcon.invoke(todo)
        }

        holder.binding.root.setOnClickListener {
            onClickItem.invoke(todo)
        }
    }

    override fun getItemCount() = myDataset.size

}

//데이터를 viewmodel이 관리하게 한다.
class MainViewModel : ViewModel() {
   var data = arrayListOf<Todo>()

    fun toggleTodo(todo: Todo) {
        todo.isDone = !todo.isDone
    }

    fun addTodo(todo: Todo) {
        data.add(todo)
    }

    fun deleteTodo(todo: Todo) {
        data.remove(todo)
    }
}
