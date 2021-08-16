package com.example.kodluyoruz_yemeksepetifinalodevi.data.remote

import com.example.kodluyoruz_yemeksepetifinalodevi.data.entity.restaurant.Restaurants
import retrofit2.Response
import retrofit2.http.GET

interface NetworkApiService {

    @GET("restaurants")
    suspend fun getRestaurants(): Response<Restaurants>

    /*@GET("restaurants/2/meals")
    suspend fun getMeals(): Response<Restaurants>*/

}