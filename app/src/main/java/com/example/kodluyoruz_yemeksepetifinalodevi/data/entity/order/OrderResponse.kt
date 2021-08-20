package com.example.kodluyoruz_yemeksepetifinalodevi.data.entity.order

import com.google.gson.annotations.SerializedName

data class OrderResponse(
    @SerializedName("data")
    val orderList: ArrayList<Orders>,
    val success: Boolean
)
