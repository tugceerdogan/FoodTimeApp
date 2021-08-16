package com.example.kodluyoruz_yemeksepetifinalodevi

import android.app.Application
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class FoodDeliveryApp : Application() {

    override fun onCreate() {
        super.onCreate()
    }

    override fun onTerminate() {
        super.onTerminate()
    }
}