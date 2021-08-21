package com.example.kodluyoruz_yemeksepetifinalodevi.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.kodluyoruz_yemeksepetifinalodevi.data.entity.profile.UserResponse
import com.example.kodluyoruz_yemeksepetifinalodevi.repository.ApiRepository
import com.example.kodluyoruz_yemeksepetifinalodevi.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class UserViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val apiRepository: ApiRepository
) : ViewModel() {

    fun logOut() {
        apiRepository.logOut()
    }

    fun getUser(): LiveData<Resource<UserResponse>> = apiRepository.getUser()

}