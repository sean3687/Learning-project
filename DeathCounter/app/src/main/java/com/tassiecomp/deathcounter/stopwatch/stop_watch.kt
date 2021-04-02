package com.tassiecomp.deathcounter.stopwatch

import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.SystemClock
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.tassiecomp.deathcounter.R
import kotlinx.android.synthetic.main.fragment_stop_watch.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [stop_watch.newInstance] factory method to
 * create an instance of this fragment.
 */
class stop_watch : Fragment() {


    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)


        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?


    ): View? {
        val inf: View = inflater.inflate(R.layout.fragment_stop_watch, container, false)
        // Inflate the layout for this fragment


        return inf
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        var pauseAt: Long = 0
        chronometer_start.setOnClickListener {
            // lap still on work
            chronometer_lap.visibility = GONE
            chronometer_reset.visibility = VISIBLE
            chronometer_pause.visibility = VISIBLE
            chronometer_start.visibility = GONE
            Log.d("fragment", "start clicked")

            chronometer.base = SystemClock.elapsedRealtime() - pauseAt
            chronometer.start()
        }

        chronometer_pause.setOnClickListener {
            var pause_status = chronometer_pause.text
            if (pause_status == "Pause") {
                chronometer_pause.text = "Resume"
                pauseAt = SystemClock.elapsedRealtime() - chronometer.base
                chronometer.stop()

            } else {
                chronometer_pause.text = "Pause"
                chronometer.base = SystemClock.elapsedRealtime() - pauseAt
                chronometer.start()
            }
        }

        chronometer_reset.setOnClickListener {

            chronometer_lap.visibility = GONE
            chronometer_reset.visibility = GONE
            chronometer_pause.visibility = GONE
            chronometer_start.visibility = VISIBLE
            chronometer.base = SystemClock.elapsedRealtime()
            chronometer.stop()
        }





    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment stop_watch.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            stop_watch().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }


}