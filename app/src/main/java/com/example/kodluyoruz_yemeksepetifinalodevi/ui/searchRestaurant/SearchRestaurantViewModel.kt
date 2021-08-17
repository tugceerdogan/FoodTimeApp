package com.example.kodluyoruz_yemeksepetifinalodevi.ui.searchRestaurant

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.kodluyoruz_yemeksepetifinalodevi.data.entity.restaurant.Restaurants
import com.example.kodluyoruz_yemeksepetifinalodevi.repository.ApiRepository
import com.example.kodluyoruz_yemeksepetifinalodevi.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchRestaurantViewModel @Inject constructor(

    val savedStateHandle: SavedStateHandle,
    private val apiRepository: ApiRepository
) : ViewModel() {
    fun searchRestaurantList(search:String): LiveData<Resource<Restaurants>> =
        apiRepository.getSearchRestaurantList(search)
}