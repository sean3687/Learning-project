package com.tassiecomp.fragmentpractice

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class FragmentOne: Fragment(){

    override fun onAttach(context: Context?) {
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        //fragment가 인터페이스를 처음으로 그릴때 호출한다.
        //activity에서는 oncreat가 호출되는 대신 fragment는 여기가 호출됨
        //activity에서는 savedInstanceState만들어오는데 inflater, container두개가 추가로 들어온다.
        //inflater는 뷰를 그려주는 역할을 한다.
        //container은 어디에 붙을지 정해준다.
        return inflater.inflate(R.layout.fragment_one,container,true)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    override fun onDetach() {
        super.onDetach()
    }
}