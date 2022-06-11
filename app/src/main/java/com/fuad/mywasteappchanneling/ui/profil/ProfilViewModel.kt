package com.fuad.mywasteappchanneling.ui.profil

import androidx.lifecycle.*
import com.fuad.mywasteappchanneling.data.repository.UserRepository
import kotlinx.coroutines.launch

class ProfilViewModel(private val userRepo: UserRepository) : ViewModel() {

//    private val _text = MutableLiveData<String>().apply {
//        value = "This is notifications Fragment"
//    }
//    val text: LiveData<String> = _text
    fun isLogin() : LiveData<Boolean> {
        return userRepo.isLogin().asLiveData()
    }

    fun logout() {
    viewModelScope.launch {
        userRepo.logout()
        }
    }
}