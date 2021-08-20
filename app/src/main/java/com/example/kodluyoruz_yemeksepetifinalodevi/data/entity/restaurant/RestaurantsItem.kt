package com.example.kodluyoruz_yemeksepetifinalodevi.data.entity.restaurant

import com.example.kodluyoruz_yemeksepetifinalodevi.data.entity.meal.MealsItem
import com.google.gson.annotations.SerializedName

data class RestaurantsItem(

    @SerializedName("_id")
    val id: String,
    @SerializedName("deliveryInfo")
    val deliveryInfo: String,
    @SerializedName("deliveryTime")
    val deliveryTime: String,
    @SerializedName("imageUrl")
    val image: String,
    @SerializedName("address")
    val address: String,
    @SerializedName("district")
    val district: String,
    @SerializedName("meals")
    val meals: ArrayList<MealsItem>,
    @SerializedName("minDeliveryFee")
    val minimumDeliveryFee: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("paymentMethods")
    val paymentMethods: String,
    @SerializedName("phone")
    val phone: String,
    @SerializedName("website")
    val website: String,
    @SerializedName("cuisine")
    val cuisine: String,
)