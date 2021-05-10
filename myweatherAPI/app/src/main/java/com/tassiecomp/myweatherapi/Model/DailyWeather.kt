package com.tassiecomp.myweatherapi.Model

import java.io.Serializable

data class DailyWeather(
    var date: Long?,
    var tempMin: Double?,
    var tempMax: Double?,
    var pop: Int?,
    var icon: String
): Serializable {

}
