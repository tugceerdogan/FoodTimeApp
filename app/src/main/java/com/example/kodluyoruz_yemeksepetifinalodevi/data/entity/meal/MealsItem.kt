package com.example.kodluyoruz_yemeksepetifinalodevi.data.entity.meal

data class MealsItem(
    val id: String,
    val imageUrl: String,
    val ingredients: String,
    val name: String,
    val price: String,
    val restaurantId: String
)