package com.example.rockscissorpaper

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.FragmentManager
import kotlinx.android.synthetic.main.activity_fragment.*

class FragmentActivity : AppCompatActivity(), FragmentOne.OnDataPassListener {

    override fun onDataPass(data: String?) {
        Log.d("pass", ""+ data)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment)
        Log.d("life_cycle", "onCreate")


        val fragmentOne: FragmentOne = FragmentOne()
        //프라그먼트에서 데이터를 넣어주는방법
        val bundle : Bundle = Bundle() //데이터를 넣어줄때는 bundle을 사용해야한다.
        bundle.putString("hello","hello")
        fragmentOne.arguments = bundle //argument에 번들을 넣어줬기때문에 꺼내줘야한다.
        //어디서 이 번들을 꺼내야하나? 바로 fragmentone Activity에 onActivityCreated





        button.setOnClickListener {
            //프라그먼트를 동적으로 작동하는방법.
            //프라그먼트붙이는방법 replace또는/add 거의 동일
            val fragmentManager: FragmentManager = supportFragmentManager

            //Transaction
            // 작업의 단위 -> 시작과 끝이 있다.
            val fragmentTransaction = fragmentManager.beginTransaction() //이 코드가 실행되는 순간 트랜섹션을
            //이제 transaction이 해야할일을 적어준다.
            fragmentTransaction.replace(R.id.container, fragmentOne)
            //이제 trasaction이 끝났다고 말해주는것.
            fragmentTransaction.commitAllowingStateLoss()
            //끝을 내는방법
            //commit ->시간 될때 해(좀더 안정적)
            //commitnow -> 지금당장해()

        }
        button2.setOnClickListener{
            //프라그먼트 remove/ detach 하는방법
            val fragmentManager = supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.detach(fragmentOne) //다시 안붙고
            //fragmentTransaction.remove(fragmentOne) //다시붙는다.
            fragmentTransaction.commit()
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d("life_cycle", "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("life_cycle", "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("life_cycle", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("life_cycle", "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("life_cycle", "onDestroy")
    }
}