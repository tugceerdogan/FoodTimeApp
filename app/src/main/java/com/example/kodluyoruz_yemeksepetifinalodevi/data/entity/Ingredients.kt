package com.example.kodluyoruz_yemeksepetifinalodevi.data.entity

import com.google.gson.annotations.SerializedName

data class Ingredients(
    @SerializedName("ingredient")
    var ingredient: String,
    @SerializedName("includes")
    var includes: Boolean

)