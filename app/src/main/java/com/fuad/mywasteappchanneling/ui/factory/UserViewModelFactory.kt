package com.fuad.mywasteappchanneling.ui.factory

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.fuad.mywasteappchanneling.data.repository.TransaksiRepository
import com.fuad.mywasteappchanneling.data.repository.UserRepository
import com.fuad.mywasteappchanneling.di.TransaksiInjecton
import com.fuad.mywasteappchanneling.di.UserInjection
import com.fuad.mywasteappchanneling.ui.login.LoginViewModel
import com.fuad.mywasteappchanneling.ui.penjemputan.PenjemputanViewModel
import com.fuad.mywasteappchanneling.ui.profil.ProfilViewModel
import com.fuad.mywasteappchanneling.ui.register.RegisterViewModel

class UserViewModelFactory(private val userRepo: UserRepository,private val addTrans : TransaksiRepository) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(RegisterViewModel::class.java) -> {
                RegisterViewModel(userRepo) as T
            }
            modelClass.isAssignableFrom(LoginViewModel::class.java) -> {
                LoginViewModel(userRepo) as T
            }modelClass.isAssignableFrom(ProfilViewModel::class.java) -> {
                ProfilViewModel(userRepo) as T
            } modelClass.isAssignableFrom(PenjemputanViewModel::class.java) -> {
                PenjemputanViewModel(addTrans, userRepo) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }

    companion object {
        @Volatile
        private var instance: UserViewModelFactory? = null
        fun getInstance(context: Context): UserViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: UserViewModelFactory(UserInjection.provideRepository(context),
                    TransaksiInjecton.provideRepository(context)
                )
            }.also { instance = it }
    }
}