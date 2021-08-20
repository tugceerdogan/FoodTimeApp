package com.example.kodluyoruz_yemeksepetifinalodevi.data.remote

import com.example.kodluyoruz_yemeksepetifinalodevi.data.entity.basket.BasketRequest
import com.example.kodluyoruz_yemeksepetifinalodevi.data.entity.basket.BasketResponse
import com.example.kodluyoruz_yemeksepetifinalodevi.data.entity.basket.Basket
import com.example.kodluyoruz_yemeksepetifinalodevi.data.entity.user.User
import com.example.kodluyoruz_yemeksepetifinalodevi.data.entity.user.UserRequest
import com.example.kodluyoruz_yemeksepetifinalodevi.data.entity.user.UserResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT

interface AuthApiService {

    @POST("a/order")
    suspend fun postOrder(@Body request: BasketRequest): Response<BasketResponse>

    @GET("a/order")
    suspend fun getOrders(): Response<Basket>

    @PUT("auth/updateDetails")
    suspend fun updateUser(@Body request : UserRequest) : Response<User>

    @GET("auth/profile")
    suspend fun getUser() : Response<UserResponse>
}