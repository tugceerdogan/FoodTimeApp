package com.example.kodluyoruz_yemeksepetifinalodevi.ui.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.kodluyoruz_yemeksepetifinalodevi.data.entity.register.RegisterRequest
import com.example.kodluyoruz_yemeksepetifinalodevi.data.entity.register.RegisterResponse
import com.example.kodluyoruz_yemeksepetifinalodevi.repository.ApiRepository
import com.example.kodluyoruz_yemeksepetifinalodevi.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    var savedStateHandle: SavedStateHandle,
    private var apiRepository: ApiRepository
) : ViewModel() {

    fun register(
        name: String,
        email: String,
        password: String
    ): LiveData<Resource<RegisterResponse>> {
        val request = RegisterRequest(name, email, password)
        return apiRepository.register(request)
    }
}
