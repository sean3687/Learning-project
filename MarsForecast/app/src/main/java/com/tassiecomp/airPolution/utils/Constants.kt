package com.tassiecomp.airPolution.utils

object Constants {
    //constant로 만들었기 때문에 가장 complile time일때 만들어진다.
    const val TAG: String = "Log"
}

enum class SEARCH_TYPE {
    //enum class또한 compile time때 만들어진다.
    PHOTO,
    USER
}