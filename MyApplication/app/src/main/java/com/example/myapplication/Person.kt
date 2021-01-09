package com.example.myapplication

import java.io.Serializable

//틀만들어주기
class PersonFromServer(
    var id : Int? = null,
    var name : String? = null,
    var age : Int? = null,
    var intro : String? = null
) : Serializable