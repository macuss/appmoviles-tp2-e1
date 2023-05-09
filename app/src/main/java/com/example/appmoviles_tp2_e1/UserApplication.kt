package com.example.appmoviles_tp2_e1

import android.app.Application

class UserApplication  : Application() {

    companion object{
        lateinit var pref:Prefs
    }

    override fun onCreate() {
        super.onCreate()
        pref = Prefs(applicationContext)


    }
}