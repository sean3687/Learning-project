package com.example.myapplication

import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_network.*
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class NetworkActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_network)

        NetworkTask(
            recycler_person,
            LayoutInflater.from(this@NetworkActivity)
        ).execute()
    }
}

class NetworkTask(
    val recyclerView: RecyclerView,
    val inflater: LayoutInflater
) : AsyncTask<Any?, Any?, Array<PersonFromServer>>() {
        override fun onPostExecute(result: Array<PersonFromServer>?) {//UI에 접근이 가능하다. 그래서 여기서 recycler view를 그려주면됨

            val adapter = PersonAdapter(result!!, inflater)
            recyclerView.adapter = adapter
            super.onPostExecute(result)
    }

    override fun doInBackground(vararg params: Any?): Array<PersonFromServer>? {
        val urlString: String = "http://mellowcode.org/json/students/"
        val url: URL = URL(urlString)
        val connection: HttpURLConnection = url.openConnection() as HttpURLConnection

        connection.requestMethod = "GET" //내가 보낸요청에 request코드가 get이라는 뜻
        connection.setRequestProperty("Content-Type","application/json")//내가보내는 속성의 타입은 content type이고 application json이다 //postman에서 header를 작성한것.

        var buffer = ""
        if (connection.responseCode == HttpURLConnection.HTTP_OK) { //http status가 ok 인 경우
            Log.d("connn", "inputstream : " + connection.inputStream)
            val reader = BufferedReader(// 글을읽을떄 한개씩이 아닌 한번에 읽겠다는뜻, 빠르게 작동하기위해서
                InputStreamReader( //inputstream을 읽을수있는것
                    connection.inputStream,
                    "UTF-8" //utf- 8형식으로 inputstream을 읽겠다는 뜻
                )
            )
            buffer = reader.readLine()
            Log.d("connn", "inputstream : " + buffer)
        }
        val data = Gson().fromJson(buffer, Array<PersonFromServer>::class.java)
        return data //return type이 nullable이기때문에 //이data는 postExcute의 result로 들어가고 type은 Array<personfromserver>로 된다.
    }

}

class PersonAdapter(
    val personList: Array<PersonFromServer>,
    val inflater: LayoutInflater
): RecyclerView.Adapter<PersonAdapter.ViewHolder>(){
    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val name : TextView
        val age : TextView
        val intro : TextView

        init {
            name = itemView.findViewById(R.id.person_name)
            age = itemView.findViewById(R.id.person_age)
            intro = itemView.findViewById(R.id.person_ment)
        }
    }

    override fun getItemCount(): Int {
        return personList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = inflater.inflate(R.layout.person_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name.setText(personList.get(position).name ?:"")
        holder.age.setText(personList.get(position).age.toString())
        holder.intro.setText(personList.get(position).intro ?:"")
    }


}

