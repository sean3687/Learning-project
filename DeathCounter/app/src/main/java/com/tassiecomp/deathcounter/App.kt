package com.tassiecomp.deathcounter

import android.app.Application
import com.google.android.gms.ads.AdView

class App: Application() {
    companion object{
        lateinit var instance:App
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}