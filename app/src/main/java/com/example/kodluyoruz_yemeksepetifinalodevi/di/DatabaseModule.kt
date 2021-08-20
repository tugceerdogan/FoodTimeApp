package com.example.kodluyoruz_yemeksepetifinalodevi.di

import android.content.Context
import com.example.kodluyoruz_yemeksepetifinalodevi.data.local.LocalDataSource
import com.example.kodluyoruz_yemeksepetifinalodevi.sharedpref.SharedPreferencesManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(ActivityRetainedComponent::class)

class DatabaseModule {

    @Provides
    fun sharedPreferencesManager(@ApplicationContext context: Context): SharedPreferencesManager {
        return SharedPreferencesManager(context)
    }

    @Provides

    fun localDataSource(sharedPreferencesManager: SharedPreferencesManager): LocalDataSource {
        return LocalDataSource(sharedPreferencesManager)
    }
}