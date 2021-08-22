package com.example.kodluyoruz_yemeksepetifinalodevi.data.remote

import com.example.kodluyoruz_yemeksepetifinalodevi.data.entity.basket.BasketAddRequest
import com.example.kodluyoruz_yemeksepetifinalodevi.data.entity.basket.BasketAddResponse
import com.example.kodluyoruz_yemeksepetifinalodevi.data.entity.basket.BasketResponse
import com.example.kodluyoruz_yemeksepetifinalodevi.data.entity.profile.UserRequest
import com.example.kodluyoruz_yemeksepetifinalodevi.data.entity.profile.UserResponse
import com.example.kodluyoruz_yemeksepetifinalodevi.data.entity.profile.User
import retrofit2.Response
import retrofit2.http.*

interface AuthApiService {


    @POST("a/order")
    suspend fun postOrder(@Body request: BasketAddRequest): Response<BasketAddResponse>

    @GET("a/order")
    suspend fun getOrders(): Response<BasketResponse>

    @POST("a/order")
    suspend fun postBasket(@Body request: BasketAddRequest): Response<BasketAddResponse>

    @GET("a/order")
    suspend fun getBasket(): Response<BasketResponse>


    @PUT("auth/updateDetails")
    suspend fun updateUser(@Body request: UserRequest): Response<User>

    @GET("auth/profile")
    suspend fun getUser(): Response<UserResponse>
}