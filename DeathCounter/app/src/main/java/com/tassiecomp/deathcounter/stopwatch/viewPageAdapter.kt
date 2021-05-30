package com.tassiecomp.deathcounter.stopwatch

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.tassiecomp.deathcounter.R
import kotlinx.android.synthetic.main.fragment_stop_watch.view.*


private const val ARG_PARAM1 = "param1"
class viewPageAdapter (supportFragmentManager: FragmentManager):FragmentPagerAdapter(supportFragmentManager){

    override fun getItem(position: Int): Fragment {
        return when (position) {

            0-> stop_watch()
            else-> timer()
        }
    }


    override fun getCount(): Int {
        return 2
    }

}