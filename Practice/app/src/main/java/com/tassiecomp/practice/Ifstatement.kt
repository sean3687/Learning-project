package com.tassiecomp.practice

fun main(array: Array<String>) {
    //반복할수있는 리스트
    val a = mutableListOf<Int>(1, 2, 3, 4, 5, 6, 7, 8, 9)

    //반복하는 방법(1)
        //반복문과 if statement
    for (item in a) { //a의 들어있는 값만큼 반복한다.
        if (item == 5) {
            println("item is Five")
        } else {
            println("item is not five")
        }
    }


    //반복하는 방법(2)
        //index는 위치값을 의미함
    for ( (index, item) in a.withIndex()){
        println("index : $index value: $item")
    }

    //반복하는방법(3)
        //a안에있는 요소를 바로 엑서스하고싶을때
    a.forEach{
        println(it) //it은 뭘의미하는가 위 반복문에서 item이라고 썻던것처럼
    }

    //반복하는방법(4)
        //a안에있는 요소들의 이름을 item으로 부를때
    a.forEach{ item ->
        println(item)
    }

    //반복하는방법(5)
        //a안에있는 요소들의 이름을 i라 부르고 index값도 가져올떄
    a.forEachIndexed { index, item ->
        println("index : $index value: $item")
    }

    //반복하는방법(6)
        //a의 사이즈직전 까지 반복해라, until은 마지막을 포함하지않는다.
    for (i in 0 until a.size) {
        println(a.get(i))
    }



    //step을 추가하는법
    //반복하는 방법 (7)
    for (i in 0 until a.size step(2)) { // 2 씩 띄엄띄엄 증가
        println(a.get(i))
    }


    //반복하는방법 (8)
    for (i in a.size -1 downTo (0)){
        //8부터 0까지 반복
        println(a.get(i))
    }

    //반복하는방법 (9)
    for (i in a.size -1 downTo(0) step(2)){
        println(a.get(i))
    }

    // 반복하는방법 (10)
        //마지막 값을 포함해서 반복한다.
    for (i in 0..a.size) {
        println(i)
    }

    // 반복하는 방법 (11)
    var b: Int = 0 // ->1 ->2 ->3
    var c: Int = 4 // ->

    //이조건을 깨는 코드가 나와야 while 문이 멈춘다.
    while (b < c) {
        b++ // while문을 정지시키기 위한코드
        println("b")
    } //만약 처음부터 b<c문이 false라면 어떻게 될까 아에 실행이안된다.

    // 반복하는방법 (12)
        //아에 실행이 안되는걸 방지하기위해 do에 필수적으로 실행될 코드를 쓰고
    do{
        println("hello")
    } while (b > c) //뒤에 반복문을 정의 한다.

}
