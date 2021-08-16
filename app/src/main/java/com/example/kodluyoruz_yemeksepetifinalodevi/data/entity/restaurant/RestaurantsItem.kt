package com.example.kodluyoruz_yemeksepetifinalodevi.data.entity.restaurant

import com.google.gson.annotations.SerializedName

data class RestaurantsItem(

    @SerializedName("name")
    val name: String,
    @SerializedName("address")
    val address: String,
    @SerializedName("score")
    val score: String,
    @SerializedName("id")
    val id: String
)