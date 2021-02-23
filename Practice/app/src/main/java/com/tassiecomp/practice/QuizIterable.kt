package com.tassiecomp.practice

fun main(array: Array<String>) {
    // odd or even
//    val a = mutableListOf<Int>(1, 2, 3, 4, 5, 6, 7, 8, 9)
//    for(item in a){
//        when{
//            item % 2 == 0 -> println("$item is even")
//            item % 2 != 0 -> println("$item is odd")
//        }
//    }

    //grading system
    val score = mutableListOf<Int>(60, 30, 80, 90, 20, 70, 80 )
    for(item in score){
        if (item in 0..59){
            println("$item is F")
        } else if (item in 60..69){
            println("$item is D")
        } else if(item in 70..79){
            println("$item is C")
        } else if(item in 80..89){
            println("$item is B")
        } else if(item in 90..100){
            println("$item is A")
        } else{
            println("score is not in range")
        }
    }
}