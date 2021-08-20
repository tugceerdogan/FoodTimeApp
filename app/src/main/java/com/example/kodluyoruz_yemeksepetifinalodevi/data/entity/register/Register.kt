package com.example.kodluyoruz_yemeksepetifinalodevi.data.entity.register

import com.google.gson.annotations.SerializedName

data class Register( @SerializedName("email")
                     val email: String,
                     @SerializedName("name")
                     val name: String,
                     @SerializedName("role")
                     val role: String)
