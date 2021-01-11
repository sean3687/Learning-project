//package com.example.myapplication
//
//import android.os.Bundle
//import android.util.Log
//import androidx.appcompat.app.AppCompatActivity
//import retrofit2.Call
//import retrofit2.Callback
//import retrofit2.Response
//import retrofit2.Retrofit
//import retrofit2.converter.gson.GsonConverterFactory
//
//
//class RetrofitActivity : AppCompatActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_retrofit)
//
//        //retrofit 생성하는 방법
//        val retrofit = Retrofit.Builder()
//            .baseUrl("http://mellowcode.org") //여기에 baseurl을 써준다. http://mellowcode.org/json/students/ 여기서 앞부분만.
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//        //서비스가 만들어지고 데이터 통신이 가능해진다.
//        val service = retrofit.create(RetrofitService::class.java)
//
//        //GET 요청
//        service.getStudentsList().enqueue(object :
//            Callback<ArrayList<PersonFromServer>> { //enqueue 는 대기줄에 올려놓는다는뜻 //통신을 대기줄에 넣어주었다는뜻
//            override fun onFailure(
//                call: Call<ArrayList<PersonFromServer>>,
//                t: Throwable
//            ) { //통신이 실패했을때
//                Log.d("retrofitt", "ERROR")
//
//            }
//
//            override fun onResponse( //통신이 성공적일때
//                call: Call<ArrayList<PersonFromServer>>,
//                response: Response<ArrayList<PersonFromServer>>
//            ) {
//                if (response.isSuccessful) {
//                    val personList = response.body()
//                    Log.d("retrofitt", "res : " + personList?.get(0)?.age)
//
//                    val code = response.code()
//                    Log.d("retrofitt", "code : " + code)
//
//                    val error = response.errorBody()
//                    val header = response.headers()
//                    Log.d("retrofitt", "code : " + header)
//                }
//            }
//        })
//        //POST 요청(1)
////        val params = HashMap<String,Any>()
////        params.put("name", "김개똥")
////        params.put("age", 20)
////        params.put("intro", "안녕하세요")
////        service.createStudent(params).enqueue(object : Callback<PersonFromServer>{
////            override fun onFailure(call: Call<PersonFromServer>, t: Throwable) {
////
////            }
////
////            override fun onResponse(
////                call: Call<PersonFromServer>,
////                response: Response<PersonFromServer>
////            ) {
////                val person = response.body()
////                Log.d("retrofitt" , "name : "+ person?.name)
////            }
////        })
//        //POST 요청 (2)
//        val person = PersonFromServer(name = "김청수", age = 12, intro = "안녕하세요 철수 입니다")
//        service.createStudentEasy(person).enqueue(object : Callback<PersonFromServer> {
//            override fun onFailure(call: Call<PersonFromServer>, t: Throwable) {
//
//            }
//
//            override fun onResponse(
//                call: Call<PersonFromServer>,
//                response: Response<PersonFromServer>
//            ) {
//                if (response.isSuccessful) {
//                    val person = response.body()
//                    Log.d("retrofitt", "name : " + person?.name)
//                }
//            }
//        })
//    }
//}