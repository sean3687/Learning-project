package com.example.rockscissorpaper

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_one.*

class FragmentOne : Fragment() {

    interface OnDataPassListener {
        fun onDataPass(data: String?)

    }

    lateinit var dataPassListener : OnDataPassListener

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d("life_cycle", "onAttach")
        dataPassListener = context as OnDataPassListener
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("life_cycle", "onCreate")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        Log.d("life_cycle", "onCreateView")
        //프라그먼트가 인터페이스를 처음으로 그릴때 호출된다. 프라그먼트는 그릴 뷰가 여기서 그리게된다.
        //inflater -> 뷰를 그려주는 역할
        //container -> 부모뷰: 프라그먼트가 붙여질곳
        return inflater.inflate(
            R.layout.fragment_one,
            container, //fragment_one에서 container라는 아이디를 가진곳에 이 fragment를 넣겠다는뜻.
            false
        ) //fragment_one xml을 그려서 Fragment위치에 넣으라는뜻
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.d("life_cycle", "FonViewCreated")
        super.onViewCreated(view, savedInstanceState)

        // fragment를 통해 하고싶은 작업 activity의 oncreate에서 했던작업을 여기서 한다.
        pass.setOnClickListener{
            dataPassListener.onDataPass("Good Bye") //goodbye라고 fragmentactivity로 전달한다.
        }

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        Log.d("life_cycle", "FonActivityCreated")

        val data = arguments?.getString("hello")
        Log.d("data",data)

        super.onActivityCreated(savedInstanceState)
    }

    override fun onStart() {
        Log.d("life_cycle", "FonStart")
        super.onStart()
    }

    override fun onResume() {
        Log.d("life_cycle", "FonResume")
        super.onResume()
    }

    override fun onPause() {
        Log.d("life_cycle", "FonPause")
        super.onPause()
    }

    override fun onStop() {
        Log.d("life_cycle", "FonStop")
        super.onStop()
    }

    override fun onDestroyView() {
        Log.d("life_cycle", "FonDestroyView")
        super.onDestroyView()
    }

    override fun onDetach() {
        Log.d("life_cycle", "FonDetach")
        super.onDetach()
    }


}