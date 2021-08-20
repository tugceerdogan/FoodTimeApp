package com.example.kodluyoruz_yemeksepetifinalodevi.data.local

import com.example.kodluyoruz_yemeksepetifinalodevi.sharedpref.SharedPreferencesManager
import javax.inject.Inject

class LocalDataSource @Inject constructor(private val sharedPreferencesManager: SharedPreferencesManager) {

    fun setToken(token: String) {
        sharedPreferencesManager.setToken(token)
    }

    fun getToken(): String? {
        return sharedPreferencesManager.getToken()
    }

}