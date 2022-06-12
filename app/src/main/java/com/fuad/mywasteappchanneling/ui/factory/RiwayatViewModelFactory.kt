package com.fuad.mywasteappchanneling.ui.factory

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.fuad.mywasteappchanneling.data.repository.RiwayatRepository
import com.fuad.mywasteappchanneling.di.RiwayatInjection
import com.fuad.mywasteappchanneling.di.TransaksiInjecton
import com.fuad.mywasteappchanneling.di.UserInjection
import com.fuad.mywasteappchanneling.ui.riwayat.RiwayatViewModel

class RiwayatViewModelFactory(private val repoRiwayat : RiwayatRepository): ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(RiwayatViewModel::class.java) -> {
                RiwayatViewModel(repoRiwayat) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }
    companion object {
        @Volatile
        private var instance: RiwayatViewModelFactory? = null
        fun getInstance(context: Context): RiwayatViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: RiwayatViewModelFactory(
                    RiwayatInjection.provideRepository(context)
                )
            }.also { instance = it }
    }
}