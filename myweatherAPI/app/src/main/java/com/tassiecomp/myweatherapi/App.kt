package com.tassiecomp.myweatherapi

import android.app.Application
import com.tassiecomp.myweatherapi.RecyclerView.DailyRecyclerViewAdapter

class App: Application() {
    companion object{
        lateinit var instance:App
            private set
        lateinit var dailyRecyclerViewAdapter: DailyRecyclerViewAdapter

    }

    override fun onCreate() {
        super.onCreate()
        instance = this



    }
}