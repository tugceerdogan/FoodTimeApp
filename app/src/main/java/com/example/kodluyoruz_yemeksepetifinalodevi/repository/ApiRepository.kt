package com.example.kodluyoruz_yemeksepetifinalodevi.repository

import com.example.kodluyoruz_yemeksepetifinalodevi.data.remote.RemoteDataSource
import com.example.kodluyoruz_yemeksepetifinalodevi.util.performNetworkOperation
import javax.inject.Inject

class ApiRepository @Inject constructor(
    private var remoteDataSource: RemoteDataSource,
){

    fun getRestaurantList() = performNetworkOperation {
        remoteDataSource.fetchRestaurants()
    }

    fun getMealList(id:String)= performNetworkOperation {
        remoteDataSource.fetchMeals(id)
    }

}