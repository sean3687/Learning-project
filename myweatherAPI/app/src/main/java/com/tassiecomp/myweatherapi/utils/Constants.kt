package com.tassiecomp.myweatherapi.utils

import android.content.Context
import android.location.Location
import android.location.LocationManager
import android.os.Parcel
import android.os.Parcelable
import androidx.core.content.ContextCompat.getSystemService
import java.util.*

//val lg = Locale.getDefault().getDisplayLanguage()

object API {
    const val CLIENT_ID: String = "627abdda3cbfc5171bf675cd1530a6d2"
    const val BASE_URL: String = "https://api.openweathermap.org"
    const val unit: String = "metric"



}

enum class RESPONSE_STATE {
    OKAY,
    FAIL
}

open class LocationManager{

}

