package com.example.kodluyoruz_yemeksepetifinalodevi.data.remote

import com.example.kodluyoruz_yemeksepetifinalodevi.data.entity.meal.Meals
import com.example.kodluyoruz_yemeksepetifinalodevi.data.entity.restaurant.Restaurants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface NetworkApiService {

    @GET("restaurants")
    suspend fun getRestaurants(): Response<Restaurants>

    @GET("restaurants/{id}/meals")
    suspend fun getMeals(@Path("id")id:Int):Response<Meals>

}