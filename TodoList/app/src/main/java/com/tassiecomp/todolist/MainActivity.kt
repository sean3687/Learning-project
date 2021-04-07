package com.tassiecomp.todolist

import android.app.Activity
import android.content.Intent
import android.graphics.Paint
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.TextView
import androidx.activity.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.tassiecomp.todolist.databinding.ActivityMainBinding
import com.tassiecomp.todolist.databinding.ItemTodoBinding


class MainActivity : AppCompatActivity() {
    val RC_SIGN_IN = 1000

    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        val view = binding.root
        setContentView(view)

        if (FirebaseAuth.getInstance().currentUser == null) {
            login()
        }


        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = TodoAdapter(
                emptyList(),
                onClickDeleteIcon = {
                    viewModel.deleteTodo(it)
                    //아래코드가 ui를 업데이트 한다.
                }, onClickItem = {
                    viewModel.toggleTodo(it)
                }
            )
        }

        binding.addButton.setOnClickListener {
            val todo = Todo(binding.editText.text.toString())
            viewModel.addTodo(todo)

        }

        //관찰 UI업데이트
        viewModel.todoLiveData.observe(this, Observer {
            (binding.recyclerView.adapter as TodoAdapter).setData(it)
        })


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val response = IdpResponse.fromResultIntent(data)

            if (resultCode == Activity.RESULT_OK) {
                // Successfully signed in
                viewModel.fetchData()

                // ...
            } else {
                // Sign in failed. If response is null the user canceled the
                // sign-in flow using the back button. Otherwise check
                // response.getError().getErrorCode() and handle the error.
                // ...
                finish()
            }
        }
    }

    fun login() {
        val providers = arrayListOf(
            AuthUI.IdpConfig.EmailBuilder().build()
        )


        startActivityForResult(
            AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(providers)
                .build(),
            RC_SIGN_IN
        )
    }


    fun logout() {
        AuthUI.getInstance()
            .signOut(this)
            .addOnCompleteListener {
                // ... when it was successful
                login()
            }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_log_out -> {
                logout()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}


data class Todo(
    val text: String,
    var isDone: Boolean = false
)


class TodoAdapter(
    private var myDataset: List<Todo>,
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

    fun setData(newData: List<Todo>) {
        myDataset = newData
        notifyDataSetChanged()
    }
}

//데이터를 viewmodel이 관리하게 한다.
class MainViewModel : ViewModel() {
    val db = Firebase.firestore
    //변경 가능하고 라이브로 업데이트해주는 데이터
    val todoLiveData = MutableLiveData<List<Todo>>()

    private val data = arrayListOf<Todo>()

    init{
        fetchData()
    }
    fun fetchData() {
        val user = FirebaseAuth.getInstance().currentUser
        if(user != null) {
        db.collection("$user")
            .get()
            .addOnSuccessListener { result ->
                data.clear()
                for (document in result) {
                    val todo = Todo(
                        document.data.get("text") as String,
                        document.data.get("isDone") as Boolean,

                        )
                    data.add(todo)
                }
                todoLiveData.value = data
            }
        }
    }

    fun toggleTodo(todo: Todo) {
        todo.isDone = !todo.isDone
        todoLiveData.value = data
    }

    fun addTodo(todo: Todo) {
        data.add(todo)
        todoLiveData.value = data
    }

    fun deleteTodo(todo: Todo) {
        data.remove(todo)
        todoLiveData.value = data
    }
}
