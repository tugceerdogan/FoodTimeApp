package com.example.kodluyoruz_yemeksepetifinalodevi.data.remote

import com.example.kodluyoruz_yemeksepetifinalodevi.data.entity.basket.BasketAddRequest
import com.example.kodluyoruz_yemeksepetifinalodevi.data.entity.profile.UserRequest
import com.example.kodluyoruz_yemeksepetifinalodevi.util.BaseDataSource
import javax.inject.Inject

class AuthRemoteDataSource @Inject constructor(private val authAPIService: AuthApiService) :
    BaseDataSource() {

    suspend fun getUser() = getResult { authAPIService.getUser() }

    suspend fun updateUser(request: UserRequest) = getResult { authAPIService.updateUser(request) }


    suspend fun getBaskets() = getResult { authAPIService.getOrders() }

    suspend fun postBaskets(basketAddRequest: BasketAddRequest) = getResult {
        authAPIService.postOrder(basketAddRequest)
    }


    suspend fun getBasket() = getResult { authAPIService.getBasket() }

    suspend fun postBasket(basketAddRequest: BasketAddRequest) = getResult {
        authAPIService.postBasket(basketAddRequest)
    }


}