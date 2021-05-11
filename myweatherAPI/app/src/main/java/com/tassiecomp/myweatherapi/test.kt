package com.tassiecomp.myweatherapi

import java.text.SimpleDateFormat

fun main(array: Array <String>){
    val sdf = SimpleDateFormat("E")
    val day = sdf.format(1620717714911)
    println(day)
}