package com.example.kodluyoruz_yemeksepetifinalodevi.data.entity.profile

import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("data")
    val user: User,
    @SerializedName("success")
    val success: Boolean
)
