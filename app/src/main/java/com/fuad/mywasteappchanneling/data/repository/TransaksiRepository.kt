package com.fuad.mywasteappchanneling.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.fuad.mywasteappchanneling.data.remote.retrofit.ApiService
import kotlinx.coroutines.flow.Flow
import java.lang.Exception
import com.fuad.mywasteappchanneling.data.Result
import com.fuad.mywasteappchanneling.data.remote.response.ResponseLogin
import com.fuad.mywasteappchanneling.data.remote.response.ResponseRegister
import kotlinx.coroutines.flow.map
import retrofit2.http.Field

class TransaksiRepository private constructor(
    private val apiService: ApiService
){

    fun addTransaction(harga: Int, berat: Int, idSampah: Int, idJasa: Int, idUser: Int) : LiveData<Result<ResponseRegister>> = liveData {
        emit(Result.Loading)
        try {
            val result = apiService.addTransaction(harga, berat, idSampah, idJasa, idUser)
            emit(Result.Success(result))
        }catch (e : Exception){
            e.printStackTrace()
            emit(Result.Error(e.message.toString()))
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: TransaksiRepository? = null

        fun getInstance(
            apiService: ApiService
        ): TransaksiRepository {
            return INSTANCE ?: synchronized(this) {
                val instance = TransaksiRepository(apiService)
                INSTANCE = instance
                instance
            }
        }
    }
}