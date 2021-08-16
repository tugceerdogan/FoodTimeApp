package com.example.kodluyoruz_yemeksepetifinalodevi.data.remote

import com.example.kodluyoruz_yemeksepetifinalodevi.util.BaseDataSource
import javax.inject.Inject


class RemoteDataSource @Inject constructor(private val apiService: NetworkApiService) :
    BaseDataSource(){

    suspend fun fetchRestaurants() = getResult { apiService.getRestaurants() }


}