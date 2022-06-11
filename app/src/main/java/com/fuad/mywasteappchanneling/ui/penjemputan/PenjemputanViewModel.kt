package com.fuad.mywasteappchanneling.ui.penjemputan

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fuad.mywasteappchanneling.data.repository.TransaksiRepository
import kotlinx.coroutines.launch

class PenjemputanViewModel(private val repo: TransaksiRepository): ViewModel() {

    fun addTransaction(harga: Int, berat: Int, idSampah: Int, idJasa: Int, idUser: Int){
        viewModelScope.launch {
            repo.addTransaction(harga, berat, idSampah, idJasa, idUser)
        }
    }
}