package com.tassiecomp.myweatherapi.Model

data class Weather(
    var thumbnail: String?,
    var author: String?,
    var createdAt: String?,
    var likesCount: Int?)
//) :  // serializable 을 가능하게 만들어야 activity 사이에서 주고 받을수있다.
//
//}
