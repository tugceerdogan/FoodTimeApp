package com.example.kodluyoruz_yemeksepetifinalodevi.data.remote

import com.example.kodluyoruz_yemeksepetifinalodevi.data.entity.basket.BasketRequest
import com.example.kodluyoruz_yemeksepetifinalodevi.data.entity.user.UserRequest
import com.example.kodluyoruz_yemeksepetifinalodevi.util.BaseDataSource
import javax.inject.Inject

class AuthRemoteDataSource @Inject constructor(private val authAPIService: AuthApiService) :
    BaseDataSource() {

    suspend fun getOrders() = getResult { authAPIService.getOrders() }

    suspend fun updateUser(request : UserRequest) = getResult { authAPIService.updateUser(request) }

    suspend fun getUser() = getResult { authAPIService.getUser() }

    suspend fun postOrder(orderAddRequest: BasketRequest) = getResult {
        authAPIService.postOrder(orderAddRequest)
    }

}