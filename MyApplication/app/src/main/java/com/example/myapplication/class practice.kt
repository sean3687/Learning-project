package com.example.myapplication

interface Person_ {
    fun eat()
    fun sleep()
}

class Student2_ : Person_{ //그냥 클래와 다르게 person다음 ()생성자가 없다.
    //왜냐하면 인스턴스화(instantiate) 시키는게 아니기때문
    // 인스턴스화 :  카테고리를 만드는 작업
    // 근데 인터페이스는 인스턴스화를 시키는게아니고 같은 기능을 가지고있다는 뜻.
    //즉, 니가 eat 과 sleep을 구현하면 person이라는 타입이다 라고 말해주는게 인터페이스
    override fun eat() {

    }

    override fun sleep() {

    }
}

