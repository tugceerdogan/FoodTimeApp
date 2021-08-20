package com.example.kodluyoruz_yemeksepetifinalodevi.data.entity.order

import com.google.gson.annotations.SerializedName

data class OrderAddRequest(
    @SerializedName("restaurantId")
    val restaurantId: String,
    @SerializedName("mealId")
    val mealId: String,
)
