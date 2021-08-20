package com.example.kodluyoruz_yemeksepetifinalodevi.ui.restaurantsDetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.kodluyoruz_yemeksepetifinalodevi.data.entity.meal.MealsResponse
import com.example.kodluyoruz_yemeksepetifinalodevi.data.entity.restaurant.RestaurantResponse
import com.example.kodluyoruz_yemeksepetifinalodevi.repository.ApiRepository
import com.example.kodluyoruz_yemeksepetifinalodevi.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RestaurantDetailViewModel @Inject constructor(

    val savedStateHandle: SavedStateHandle,
    val apiRepository: ApiRepository
) : ViewModel() {
    fun getRestaurantDetail(id: String): LiveData<Resource<RestaurantResponse>> =
        apiRepository.getRestaurantById(id)
}