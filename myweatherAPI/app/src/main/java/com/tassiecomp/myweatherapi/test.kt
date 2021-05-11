package com.tassiecomp.myweatherapi

import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*


fun main(array: Array <String>){
    val formatter: DateFormat = SimpleDateFormat("E")
    val calendar = Calendar.getInstance()

    calendar.timeInMillis = 1620788400000
    println(calendar)
    println(formatter.format(calendar.time))



}


