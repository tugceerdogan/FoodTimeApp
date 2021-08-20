package com.example.kodluyoruz_yemeksepetifinalodevi.data.entity.restaurant

import com.google.gson.annotations.SerializedName

data class Restaurants(
    @SerializedName("count")
    val count: Int,
    @SerializedName("data")
    val restaurantList: List<RestaurantsItem>,
    @SerializedName("success")
    val success: Boolean
)