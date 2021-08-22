package com.example.kodluyoruz_yemeksepetifinalodevi.data.entity.basket

import com.example.kodluyoruz_yemeksepetifinalodevi.data.entity.meal.MealsItem
import com.example.kodluyoruz_yemeksepetifinalodevi.data.entity.restaurant.OrderRest
import com.google.gson.annotations.SerializedName
import java.util.*

data class Basket(
    @SerializedName("_id")
    val id: String,
    @SerializedName("createdDate")
    val createdDate: Date,
    @SerializedName("meal")
    val meal: MealsItem,
    @SerializedName("restaurant")
    val restaurant: OrderRest,
    @SerializedName("price")
    val price: Double
)
