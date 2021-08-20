package com.example.kodluyoruz_yemeksepetifinalodevi.data.entity.meal

import com.google.gson.annotations.SerializedName

data class MealsItem(

    @SerializedName("_id")
    val id: String,

    @SerializedName("description")
    val description: String,

    @SerializedName("imageUrl")
    val imageUrl: String,

    @SerializedName("ingredients")
    val ingredients: ArrayList<String>,

    @SerializedName("name")
    val name: String,

    @SerializedName("price")
    val price: String,



)