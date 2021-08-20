package com.example.kodluyoruz_yemeksepetifinalodevi.data.entity.register

import com.google.gson.annotations.SerializedName

data class RegisterResponse(
    @SerializedName("data")
    val registerData: Register,
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("token")
    val token: String
)