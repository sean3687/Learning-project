package com.tassiecomp.deathcounter.stopwatch

import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Toast
import com.tassiecomp.deathcounter.App
import com.tassiecomp.deathcounter.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_timer.*
import java.math.BigDecimal
import java.math.RoundingMode
import kotlin.math.roundToInt

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [timer.newInstance] factory method to
 * create an instance of this fragment.
 */
class timer : Fragment() {
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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_timer, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment timer.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            timer().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        countdown_start.setOnClickListener {
            //if is null set 0
            var input_HH = HH_edit?.text.toString().toLong()
            var input_MM = MM_edit?.text.toString().toLong()
            var input_SS = SS_edit?.text.toString().toLong()
            var runtimerMilli = (input_HH + input_MM + input_SS )

            CountdownTimer(runtimerMilli, runtimerMilli * 100)
            countdown_settimer.visibility = GONE
            countdown_pause.visibility = VISIBLE
            countdown_reset.visibility = VISIBLE
            chronometer_timer.visibility = VISIBLE

        }




        countdown_pause.setOnClickListener {

        }

        countdown_reset.setOnClickListener {
            countdown_settimer.visibility = VISIBLE
        }

    }

    //COUNTDOWN SUB-VIEW
    fun CountdownTimer(input_millisecond: Long, input_max_millisecond: Long) {

        object : CountDownTimer(input_millisecond, 100) {
            override fun onTick(millisUntilFinished: Long) {
                val milli = (millisUntilFinished.toFloat() / 100.0f).roundToInt()
                val sec = (milli.toFloat() / 10.0f).roundToInt()
                val min = (sec.toFloat() / 60.0f).roundToInt()
                val hour = (min.toFloat() / 60.0f).roundToInt()

                val progress_remain_percent =
                    ((BigDecimal(millisUntilFinished.toDouble() / input_max_millisecond * 1000).setScale(
                        1,
                        RoundingMode.HALF_EVEN
                    )).toFloat())
                Log.d("TAGG", "$progress_remain_percent")
                chronometer_timer.setText("${hour}:${min}:${sec}:${milli}")

            }

            override fun onFinish() {
                Log.d("second", "done!")
            }
        }.start()
    }
//COUNTDOWN SUB-VIEW END
}