package com.fuad.mywasteappchanneling.ui.penjemputan

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.fuad.mywasteappchanneling.data.repository.TransaksiRepository
import com.fuad.mywasteappchanneling.data.repository.UserRepository
import kotlinx.coroutines.launch

class PenjemputanViewModel(private val repo: TransaksiRepository, private val userRepository: UserRepository): ViewModel() {

    fun addTransaction(harga: Int, berat: Int, idSampah: Int, idJasa: Int, idUser: Int) = repo.addTransaction(harga, berat, idSampah, idJasa, idUser)

    fun getUserId() = userRepository.getUserId().asLiveData()
}