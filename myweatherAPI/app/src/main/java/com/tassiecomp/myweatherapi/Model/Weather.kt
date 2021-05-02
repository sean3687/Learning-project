package com.tassiecomp.myweatherapi.Model

import java.io.Serializable

data class Weather(
    var main_temp: Int?,
    var main_feelslike: Int?,
    var main_mintemp: Int?,
    var main_maxtemp: Int?,
    var main_humidity: Int?,
    var wind_speed: Double?,
    var wind_deg:Int?,
    var weather_description:String?,
    var weather_icon:String?,
    var name_city:String?
    ): Serializable{

    }
//) :  // serializable 을 가능하게 만들어야 activity 사이에서 주고 받을수있다.
//
//}
