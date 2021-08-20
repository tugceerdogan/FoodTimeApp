package com.example.kodluyoruz_yemeksepetifinalodevi.data.entity.login

import com.google.gson.annotations.SerializedName

data class LoginRequest(
    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val password: String
)
