package com.tassiecomp.myretrofittrial.utils

object Constants{

}

enum class SEARCH_TYPE {
    //enum class또한 compile time때 만들어진다.
    PHOTO,
    USER
}


enum class RESPONSE_STATE {
    OKAY,
    FAIL
}

object API{
    const val searchPhotos = "search/photos"
    const val searchUsers = "search/users"
    const val BASE_URL = "https://api.unsplash.com/"
    const val CLIENT_ID = "iv39LSbT2iWcTbGPBAZ1zeEcdObP52KBvT_s_A2r3MU"


}

