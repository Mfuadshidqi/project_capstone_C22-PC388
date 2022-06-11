package com.fuad.mywasteappchanneling.ui.register

import androidx.lifecycle.ViewModel
import com.fuad.mywasteappchanneling.data.repository.UserRepository

class RegisterViewModel(private val repo: UserRepository) : ViewModel() {

    fun register(name: String, email: String, password: String, alamat: String) = repo.register(name, email, password, alamat)
}