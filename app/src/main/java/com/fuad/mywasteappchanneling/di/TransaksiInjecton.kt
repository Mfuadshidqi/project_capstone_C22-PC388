package com.fuad.mywasteappchanneling.di

import android.content.Context
import com.fuad.mywasteappchanneling.data.remote.retrofit.ApiConfig
import com.fuad.mywasteappchanneling.data.repository.TransaksiRepository

object TransaksiInjecton {
    fun provideRepository(context: Context): TransaksiRepository {
        val apiService = ApiConfig.getApiService()
        return TransaksiRepository.getInstance( apiService)
    }
}