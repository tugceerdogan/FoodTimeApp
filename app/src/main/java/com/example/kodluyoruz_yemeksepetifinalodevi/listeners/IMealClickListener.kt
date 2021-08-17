package com.example.kodluyoruz_yemeksepetifinalodevi.listeners

import com.example.kodluyoruz_yemeksepetifinalodevi.data.entity.meal.MealsItem

interface IMealClickListener {
    fun onClick(name: MealsItem)
}