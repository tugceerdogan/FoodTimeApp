package com.example.kodluyoruz_yemeksepetifinalodevi.ui.mealDetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.kodluyoruz_yemeksepetifinalodevi.data.entity.meal.MealsItem
import com.example.kodluyoruz_yemeksepetifinalodevi.data.entity.meal.MealsResponse
import com.example.kodluyoruz_yemeksepetifinalodevi.data.entity.order.OrderAddRequest
import com.example.kodluyoruz_yemeksepetifinalodevi.data.entity.order.OrderAddResponse
import com.example.kodluyoruz_yemeksepetifinalodevi.repository.ApiRepository
import com.example.kodluyoruz_yemeksepetifinalodevi.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MealDetailsViewModel @Inject constructor(
    var savedStateHandle: SavedStateHandle,
    private var apiRepository: ApiRepository
) : ViewModel() {
    var meal: MealsItem? = null

    fun getMealDetails(id: String): LiveData<Resource<MealsResponse>> {
        return apiRepository.getMealById(id)
    }

    fun postOrder(orderAddRequest: OrderAddRequest): LiveData<Resource<OrderAddResponse>> =
        apiRepository.postOrder(orderAddRequest)
}