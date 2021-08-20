package com.example.kodluyoruz_yemeksepetifinalodevi.data.entity.basket

import com.google.gson.annotations.SerializedName

data class BasketAddRequest(
    @SerializedName("restaurantId")
    val restaurantId: String,
    @SerializedName("mealId")
    val mealId: String,
)
