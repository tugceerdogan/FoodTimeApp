package com.example.kodluyoruz_yemeksepetifinalodevi.sharedpref

import android.content.Context
import android.content.SharedPreferences

class SharedPreferencesManager(context: Context) {
    companion object {
        const val TOKEN = "com.example.kodluyoruz_yemeksepetifinalodevi.TOKEN"
    }

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("sharedPreferencesUtil", Context.MODE_PRIVATE)

    fun setToken(token: String) {
        sharedPreferences.edit().putString(TOKEN, token).apply()
    }

    fun getToken(): String? {
        return sharedPreferences.getString(TOKEN, "")
    }

    fun setOnboardingSeen() {
        sharedPreferences.edit().putBoolean("ONBOARDING", true).apply()
    }

    fun isOnboardingSeen(): Boolean {
        return sharedPreferences.getBoolean("ONBOARDING", false)
    }
}