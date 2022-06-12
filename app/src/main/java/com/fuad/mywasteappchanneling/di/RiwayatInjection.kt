package com.fuad.mywasteappchanneling.di

import android.content.Context
import com.fuad.mywasteappchanneling.data.remote.retrofit.ApiConfig
import com.fuad.mywasteappchanneling.data.repository.RiwayatRepository
import com.fuad.mywasteappchanneling.data.repository.TransaksiRepository

object RiwayatInjection {
    fun provideRepository(context: Context): RiwayatRepository {
        val apiService = ApiConfig.getApiService()
        return RiwayatRepository.getInstance( apiService)
    }
}