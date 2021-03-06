package com.tassiecomp.myweatherapi.api

import android.util.Log
import com.google.gson.JsonElement
import com.tassiecomp.myweatherapi.Model.DailyWeather
import com.tassiecomp.myweatherapi.Model.Weather
import com.tassiecomp.myweatherapi.utils.API
import com.tassiecomp.myweatherapi.utils.API.unit
import com.tassiecomp.myweatherapi.utils.RESPONSE_STATE
import retrofit2.Call
import retrofit2.Response
import java.text.SimpleDateFormat

class RetrofitManager {

    companion object {
        val instance = RetrofitManager()

    }


    private val iRetrofit: IRetrofit? =
        RetrofitClient.getClient(API.BASE_URL)?.create(IRetrofit::class.java)

    fun getData_byCity(
        searchTerm: String?,
        completion: (RESPONSE_STATE, ArrayList<Weather>?) -> Unit
    ) {
        val term = searchTerm.let {
            it
        } ?: ""

        val call = iRetrofit?.getCityData(searchTerm = term, unit = unit).let {
            it
        } ?: return

        call.enqueue(object : retrofit2.Callback<JsonElement> {
            override fun onResponse(call: Call<JsonElement>, response: Response<JsonElement>) {
                Log.d(
                    "TAG",
                    "RetrofitManager - onResponse() called() / response: ${response.body()}"
                )

                when (response.code()) {
                    200 ->
                        response.body()?.let {
                            //response.body값이 있다면
                            val parsedWeatherArray = ArrayList<Weather>()
                            val body = it.asJsonObject

                            //JSONArray
                            val weather = body.getAsJsonArray("weather")
                            val main = body.getAsJsonObject("main")
                            val wind = body.getAsJsonObject("wind")


                            //JSONArray - Items

                            val main_temp = main.get("temp").asInt
                            val main_feelslike = main.get("feels_like").asInt
                            val main_mintemp = main.get("temp_min").asInt
                            val main_maxtemp = main.get("temp_max").asInt
                            val main_humidity = main.get("humidity").asInt
                            val wind_speed = wind.get("speed").asDouble
                            val wind_deg = wind.get("deg").asInt
                            val city_name = body.get("name").asString


                            weather.forEach { weatherItem ->
                                val weatherItemObject = weatherItem.asJsonObject

                                val weather_Description =
                                    weatherItemObject.get("description").asString
                                val weather_Icon =
                                    weatherItemObject.get("icon").asString


                                //adding First item on list
                                val weatherItem = Weather(
                                    main_temp = main_temp,
                                    main_feelslike = main_feelslike,
                                    main_mintemp = main_mintemp,
                                    main_maxtemp = main_maxtemp,
                                    main_humidity = main_humidity,
                                    wind_speed = wind_speed,
                                    wind_deg = wind_deg,
                                    weather_description = weather_Description,
                                    weather_icon = weather_Icon,
                                    name_city = city_name
                                )

                                parsedWeatherArray.add(weatherItem)


                                Log.d(
                                    "mainData",
                                    "weatherDescription:$parsedWeatherArray /n weather_description: $main"
                                )

                            }
                            completion(RESPONSE_STATE.OKAY, parsedWeatherArray)


                        }


                }

            }

            override fun onFailure(call: Call<JsonElement>, t: Throwable) {
                Log.d("TAG", "RetrofitManager - onFailure() called() / t: $t")
                //이제 호출된 결과값이 들어온다.
                completion(RESPONSE_STATE.FAIL, null)
            }

        })


    }


    fun getData_byGrid(
        latitude: Double?,
        longitude: Double?,
        units: String?,
        completion: (RESPONSE_STATE, ArrayList<Weather>?) -> Unit
    ) {
        val lat = latitude.let {
            it
        } ?: ""

        val lon = longitude.let {
            it
        } ?: ""
        val unit = units.let {
            it
        } ?: ""

        val call = iRetrofit?.getGridData(
            latitude = lat as Double,
            longitude = lon as Double,
            unit = unit as String,
        ).let {
            it
        } ?: return

        call.enqueue(object : retrofit2.Callback<JsonElement> {
            override fun onResponse(call: Call<JsonElement>, response: Response<JsonElement>) {
                Log.d(
                    "TAG",
                    "RetrofitManager - onResponse() called() / response: ${response.body()}"
                )

                when (response.code()) {
                    200 -> {
                        //response.body값이 있다면
                        response.body()?.let {
                            val parsedWeatherArray = ArrayList<Weather>()
                            val body = it.asJsonObject

                            //JSONArray
                            val weather = body.getAsJsonArray("weather")
                            val main = body.getAsJsonObject("main")
                            val wind = body.getAsJsonObject("wind")


                            //JSONArray - Items

                            val main_temp = main.get("temp").asInt
                            val main_feelslike = main.get("feels_like").asInt
                            val main_mintemp = main.get("temp_min").asInt
                            val main_maxtemp = main.get("temp_max").asInt
                            val main_humidity = main.get("humidity").asInt
                            val wind_speed = wind.get("speed").asDouble
                            val wind_deg = wind.get("deg").asInt
                            val city_name = body.get("name").asString


                            weather.forEach { weatherItem ->
                                val weatherItemObject = weatherItem.asJsonObject

                                val weather_Description =
                                    weatherItemObject.get("description").asString
                                val weather_Icon =
                                    weatherItemObject.get("icon").asString


                                //adding First item on list
                                val weatherItem = Weather(
                                    main_temp = main_temp,
                                    main_feelslike = main_feelslike,
                                    main_mintemp = main_mintemp,
                                    main_maxtemp = main_maxtemp,
                                    main_humidity = main_humidity,
                                    wind_speed = wind_speed,
                                    wind_deg = wind_deg,
                                    weather_description = weather_Description,
                                    weather_icon = weather_Icon,
                                    name_city = city_name
                                )

                                parsedWeatherArray.add(weatherItem)


                                Log.d(
                                    "mainData",
                                    "weatherDescription:$parsedWeatherArray /n weather_description: $main"
                                )

                            }
                            completion(RESPONSE_STATE.OKAY, parsedWeatherArray)
                        }


                    }
                }

            }


            override fun onFailure(call: Call<JsonElement>, t: Throwable) {
                Log.d("TAG", "RetrofitManager - onFailure() called() / t: $t")
                //이제 호출된 결과값이 들어온다.
                completion(RESPONSE_STATE.FAIL, null)
            }
        })


    }

    fun getData_byDaily(
        latitude: Double?,
        longitude: Double?,
        units: String?,
        exclude: String?,
        completion: (RESPONSE_STATE, ArrayList<DailyWeather>?) -> Unit
    ) {
        val lat = latitude.let {
            it
        } ?: ""

        val lon = longitude.let {
            it
        } ?: ""
        val unit = units.let {
            it
        } ?: ""
        val exclude = exclude.let {
            it
        } ?: ""

        val call = iRetrofit?.getDailyData(
            latitude = lat as Double,
            longitude = lon as Double,
            unit = unit as String,
            exclude = exclude as String
        ).let {
            it
        } ?: return

        call.enqueue(object : retrofit2.Callback<JsonElement> {
            override fun onResponse(call: Call<JsonElement>, response: Response<JsonElement>) {
                when (response.code()) {
                    200 -> {
                        response.body()?.let {
                            var body = it.asJsonObject
                            var parsedDailyWeatherArray = ArrayList<DailyWeather>()

                            val list = body.getAsJsonArray("daily")
                            list.forEach { listItem ->


                                val listItemObject = listItem.asJsonObject //array안에 {}하나 가져온것

                                val date = listItemObject.get("dt").asLong
                                val day = getday(date*1000)

                                val tempObject = listItemObject.getAsJsonObject("temp")
                                val tempMin = tempObject.get("min").asDouble
                                val tempMax = tempObject.get("max").asDouble
                                val pop =
                                    (listItemObject.get("pop").asFloat*100).toInt() //probability of precipitation

                                val weatherArray =
                                    listItemObject.getAsJsonArray("weather")
                                    weatherArray.forEach{weatherList->
                                        val weatherObject = weatherList.asJsonObject
                                        val icon = weatherObject.get("icon").asString
                                        val dailyweather = DailyWeather(
                                            date = day,
                                            tempMin = tempMin,
                                            tempMax = tempMax,
                                            pop = pop,
                                            icon = icon,
                                        )
                                        parsedDailyWeatherArray.add(dailyweather)

                                    }


                            }
                            completion(RESPONSE_STATE.OKAY, parsedDailyWeatherArray)
                            Log.d("parsingddddddddd", "$parsedDailyWeatherArray")


                        }


                    }
                }


            }

            override fun onFailure(call: Call<JsonElement>, t: Throwable) {
                completion(RESPONSE_STATE.FAIL, null)

            }

        })
    }

    fun getday(millisecond:Long):String{
        val sdf = SimpleDateFormat("E")
        var day = sdf.format(millisecond)
        return day
    }
}


