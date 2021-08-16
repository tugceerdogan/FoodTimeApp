package com.example.kodluyoruz_yemeksepetifinalodevi.ui.restaurants

import androidx.lifecycle.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import com.example.kodluyoruz_yemeksepetifinalodevi.data.entity.restaurant.Restaurants
import com.example.kodluyoruz_yemeksepetifinalodevi.repository.ApiRepository
import com.example.kodluyoruz_yemeksepetifinalodevi.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RestaurantListViewModel @Inject constructor(

    val savedStateHandle: SavedStateHandle,
    val apiRepository: ApiRepository
) : ViewModel() {
    fun fetchHospitalList(): LiveData<Resource<Restaurants>> =
        apiRepository.getRestaurantList()
}