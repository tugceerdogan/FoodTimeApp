package com.example.kodluyoruz_yemeksepetifinalodevi.data.entity.login

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("data")
    val loginData: Login,
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("token")
    val token: String
)