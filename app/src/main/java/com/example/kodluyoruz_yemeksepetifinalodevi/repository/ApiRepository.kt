package com.example.kodluyoruz_yemeksepetifinalodevi.repository

import com.example.kodluyoruz_yemeksepetifinalodevi.data.entity.login.LoginRequest
import com.example.kodluyoruz_yemeksepetifinalodevi.data.entity.basket.BasketAddRequest
import com.example.kodluyoruz_yemeksepetifinalodevi.data.entity.profile.UserRequest
import com.example.kodluyoruz_yemeksepetifinalodevi.data.entity.register.RegisterRequest
import com.example.kodluyoruz_yemeksepetifinalodevi.data.local.LocalDataSource
import com.example.kodluyoruz_yemeksepetifinalodevi.data.remote.AuthRemoteDataSource
import com.example.kodluyoruz_yemeksepetifinalodevi.data.remote.RemoteDataSource
import com.example.kodluyoruz_yemeksepetifinalodevi.util.performAuthTokenNetworkOperation
import com.example.kodluyoruz_yemeksepetifinalodevi.util.performNetworkOperation
import javax.inject.Inject


class ApiRepository @Inject constructor(
    private var remoteDataSource: RemoteDataSource,
    private var authRemoteDataSource: AuthRemoteDataSource,
    private var localDataSource: LocalDataSource
) {

    fun login(request: LoginRequest) = performAuthTokenNetworkOperation(
        call = {
            remoteDataSource.postLogin(request)
        },
        saveToken = {
            localDataSource.setToken(it)
        }
    )

    fun register(request: RegisterRequest) = performAuthTokenNetworkOperation(
        call = {
            remoteDataSource.postRegister(request)
        },
        saveToken = {
            localDataSource.setToken(it)
        }
    )

    fun getRestaurants() =
        performNetworkOperation {
            remoteDataSource.getRestaurants()
        }

    fun getRestaurantById(id: String) =
        performNetworkOperation {
            remoteDataSource.getRestaurantById(id)
        }


    fun getMealById(id: String) =
        performNetworkOperation {
            remoteDataSource.getMealById(id)
        }


    fun getBasket() =
        performNetworkOperation {
            authRemoteDataSource.getBaskets()
        }

    fun getUser() = performNetworkOperation {
        authRemoteDataSource.getUser()
    }

    fun updateUser(user: UserRequest) = performNetworkOperation {
        authRemoteDataSource.updateUser(request = user)
    }

    fun postBasket(basketAddRequest: BasketAddRequest) =
        performNetworkOperation {
            authRemoteDataSource.postBaskets(basketAddRequest)
        }

    fun logOut() {
        localDataSource.setToken("")
    }

}