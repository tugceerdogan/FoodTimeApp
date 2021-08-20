package com.example.kodluyoruz_yemeksepetifinalodevi.data.entity.login

import com.google.gson.annotations.SerializedName

data class Login(
    @SerializedName("email")
    val email: String,
    @SerializedName("name")
    val name: String,
    /*@SerializedName("role")
    val role: String*/
)
