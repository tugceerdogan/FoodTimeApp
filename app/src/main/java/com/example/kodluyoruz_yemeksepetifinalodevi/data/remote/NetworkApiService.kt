package com.example.kodluyoruz_yemeksepetifinalodevi.data.remote

import com.example.kodluyoruz_yemeksepetifinalodevi.data.entity.login.LoginRequest
import com.example.kodluyoruz_yemeksepetifinalodevi.data.entity.login.LoginResponse
import com.example.kodluyoruz_yemeksepetifinalodevi.data.entity.meal.MealsResponse
import com.example.kodluyoruz_yemeksepetifinalodevi.data.entity.register.RegisterRequest
import com.example.kodluyoruz_yemeksepetifinalodevi.data.entity.register.RegisterResponse
import com.example.kodluyoruz_yemeksepetifinalodevi.data.entity.restaurant.RestaurantResponse
import com.example.kodluyoruz_yemeksepetifinalodevi.data.entity.restaurant.Restaurants
import retrofit2.Response
import retrofit2.http.*

interface NetworkApiService {

    @GET("a/restaurant")
    suspend fun getRestaurants(): Response<Restaurants>

    @GET("a/restaurant/cuisine/{cuisineName}")
    suspend fun getRestaurantsByCuisine(@Path("cuisineName") cuisine: String): Response<Restaurants>

    @GET("a/restaurant/{id}")
    suspend fun getRestaurantById(@Path("id") id: String): Response<RestaurantResponse>

    @GET("a/meal/{id}")
    suspend fun getMealById(@Path("id") id: String): Response<MealsResponse>

    @POST("auth/login")
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>

    @POST("auth/register")
    suspend fun register(@Body request: RegisterRequest): Response<RegisterResponse>

}