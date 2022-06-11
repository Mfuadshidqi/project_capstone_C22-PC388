package com.fuad.mywasteappchanneling.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.fuad.mywasteappchanneling.data.remote.retrofit.ApiService
import kotlinx.coroutines.flow.Flow
import java.lang.Exception
import com.fuad.mywasteappchanneling.data.Result
import com.fuad.mywasteappchanneling.data.remote.response.ResponseLogin
import com.fuad.mywasteappchanneling.data.remote.response.ResponseRegister
import kotlinx.coroutines.flow.map

class UserRepository private constructor(
    private val dataStore: DataStore<Preferences>,
    private val apiService: ApiService
){
    fun register(name: String, email: String, password: String, alamat: String) : LiveData<Result<ResponseRegister>> = liveData {
        emit(Result.Loading)
        try {
            val result = apiService.register(name, email, password, alamat )
            emit(Result.Success(result))
        }catch (e : Exception){
            e.printStackTrace()
            emit(Result.Error(e.message.toString()))
        }
    }

    fun login(email: String, password: String) : LiveData<Result<ResponseLogin>> = liveData {
        emit(Result.Loading)
        try {
            val result = apiService.login(email, password)
            emit(Result.Success(result))
        }catch (e : Exception){
            e.printStackTrace()
            emit(Result.Error(e.message.toString()))
        }
    }

    fun getToken() : Flow<String> {
        return dataStore.data.map { preferences ->
            preferences[TOKEN] ?: ""
        }
    }

    fun isLogin() : Flow<Boolean> {
        return dataStore.data.map { preferences ->
            preferences[STATE_KEY] ?: false
        }
    }

    suspend fun setToken(token: String, isLogin: Boolean, idUser: Int) {
        dataStore.edit { preferences ->
            preferences[TOKEN] = token
            preferences[STATE_KEY] = isLogin
            preferences[ID_USER] = idUser
        }
    }

    suspend fun logout() {
        dataStore.edit { preferences ->
            preferences[TOKEN] = ""
            preferences[STATE_KEY] = false
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: UserRepository? = null

        private val TOKEN = stringPreferencesKey("token")
        private val ID_USER = intPreferencesKey("id_user")
        private val STATE_KEY = booleanPreferencesKey("state")

        fun getInstance(
            dataStore: DataStore<Preferences>,
            apiService: ApiService
        ): UserRepository {
            return INSTANCE ?: synchronized(this) {
                val instance = UserRepository(dataStore, apiService)
                INSTANCE = instance
                instance
            }
        }
    }
}