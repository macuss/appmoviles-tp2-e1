package com.example.appmoviles_tp2_e1

import android.content.Context

class Prefs(val context:Context) {
    val SHARED_NAME = "MyDB"
    val SHARED_TOP_SCORE = "Top-Score"

    val storage = context.getSharedPreferences(SHARED_NAME, 0)

    fun saveTopScore(score:String){
        storage.edit().putString(SHARED_TOP_SCORE, score).apply()
    }

    fun getTopScore(): String{
        return storage.getString(SHARED_TOP_SCORE,"")!!
    }

}