package com.tassiecomp.myweatherapi.Model

import java.io.Serializable

data class Weather(
    var main_temp: Double?,
    var main_feelslike: Float?,
    var main_mintemp: Double?,
    var main_maxtemp: Double?,
    var main_humidity: Int?,
    var wind_speed: Double?,
    var wind_deg:Int?,
    var weather_description:String?,
    var weather_icon:String?
    ): Serializable{

    }
//) :  // serializable 을 가능하게 만들어야 activity 사이에서 주고 받을수있다.
//
//}
