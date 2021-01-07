package com.example.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.realm.Realm.getDefaultInstance
import io.realm.Realm.setDefaultConfiguration
import io.realm.RealmConfiguration


class Realm : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_realm)

        Realm.init(this@RealmActivity)
        val config: RealmConfiguration = RealmConfiguration.Builder()
            .deleteRealmIfMigrationNeeded()
            .build()
        Realm.setDefaultConfiguration(config)
        val realm = Realm.getDefaultInstance()
        val realm =
    }

}