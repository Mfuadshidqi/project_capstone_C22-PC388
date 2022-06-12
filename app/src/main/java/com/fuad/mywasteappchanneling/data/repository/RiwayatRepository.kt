package com.fuad.mywasteappchanneling.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.fuad.mywasteappchanneling.data.Result
import com.fuad.mywasteappchanneling.data.remote.response.ResponseRiwayat
import com.fuad.mywasteappchanneling.data.remote.retrofit.ApiService
import java.lang.Exception

class RiwayatRepository private constructor(
    private val apiService: ApiService
){
    fun addRiwayat( ) : LiveData<Result<ResponseRiwayat>> = liveData {
        emit(Result.Loading)
        try {
            val result = apiService.getTransaction()
            emit(Result.Success(result))
        }catch (e : Exception){
            e.printStackTrace()
            emit(Result.Error(e.message.toString()))
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: RiwayatRepository? = null

        fun getInstance(
            apiService: ApiService
        ): RiwayatRepository {
            return INSTANCE ?: synchronized(this) {
                val instance = RiwayatRepository(apiService)
                INSTANCE = instance
                instance
            }
        }
    }
}