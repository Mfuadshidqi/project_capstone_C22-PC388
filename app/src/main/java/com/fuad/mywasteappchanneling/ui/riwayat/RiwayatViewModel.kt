package com.fuad.mywasteappchanneling.ui.riwayat

import androidx.lifecycle.ViewModel
import com.fuad.mywasteappchanneling.data.repository.RiwayatRepository

class RiwayatViewModel (private val repo: RiwayatRepository) : ViewModel() {
    fun getTransaction () = repo.getRiwayat()
}