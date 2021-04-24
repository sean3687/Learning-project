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

enum class RESPONSE_STATUS {
    OKAY,
    FAIL,
    NO_CONTENT
}


object API {
    const val BASE_URL: String = "https://api.unsplash.com/"
    const val CLIENT_ID: String = "ThPbf-upCIV58DbLF5xEgcjqZixJKyTTyctwPCEjXns"
    const val SEARCH_PHOTOS: String = "search/photos"
    const val SEARCH_USERS: String = "search/users"

}