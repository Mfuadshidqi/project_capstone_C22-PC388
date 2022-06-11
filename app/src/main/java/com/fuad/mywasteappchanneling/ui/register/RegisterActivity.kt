package com.fuad.mywasteappchanneling.ui.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import com.fuad.mywasteappchanneling.R
import com.fuad.mywasteappchanneling.databinding.ActivityRegisterBinding
import com.fuad.mywasteappchanneling.ui.factory.UserViewModelFactory
import com.fuad.mywasteappchanneling.data.Result
import com.fuad.mywasteappchanneling.ui.login.LoginActivity
import com.fuad.mywasteappchanneling.utils.animateVisibility

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var registerViewModel: RegisterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        setupViewModel()
        setupAction()
    }

    private fun setupViewModel() {
        val factory: UserViewModelFactory = UserViewModelFactory.getInstance(this)
        registerViewModel = ViewModelProvider(this, factory)[RegisterViewModel::class.java]

    }

    private fun setupAction() {
        binding.btnDaftar.setOnClickListener {
            val name = binding.edtName.text.toString().trim()
            val email = binding.edtEmail.text.toString().trim()
            val password = binding.edtPassword.text.toString().trim()
            val alamat = binding.edtAlamat.text.toString().trim()
            when {
                name.isEmpty() -> {
                    binding.edtName.error = resources.getString(R.string.message_validation, "name")
                }
                email.isEmpty() -> {
                    binding.edtEmail.error = resources.getString(R.string.message_validation, "email")
                }
                password.isEmpty() -> {
                    binding.edtPassword.error = resources.getString(R.string.message_validation, "password")
                }
                alamat.isEmpty() -> {
                    binding.edtAlamat.error = resources.getString(R.string.message_validation, "alamat")
                }
                else -> {
                    registerViewModel.register(name, email, password, alamat).observe(this){ result ->
                        if (result != null){
                            when(result) {
                                is Result.Loading -> {
                                    showLoading(true)
                                }
                                is Result.Success -> {
                                    showLoading(false)

                                        AlertDialog.Builder(this@RegisterActivity).apply {
                                            setTitle("Yeah!")
                                            setMessage("Your account successfully created!")
                                            setPositiveButton("Next") { _ , _ ->
                                                finish()
                                            }
                                            create()
                                            show()
                                        }
                                }
                                is Result.Error -> {
                                    showLoading(false)
                                    Toast.makeText(
                                        this,
                                        resources.getString(R.string.signup_error),
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }
                        }
                    }
                }
            }
        }

        binding.tvDisiniLog.setOnClickListener{
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.apply {
            edtEmail.isEnabled = !isLoading
            edtPassword.isEnabled = !isLoading
            edtName.isEnabled = !isLoading
            btnDaftar.isEnabled = !isLoading

            if (isLoading) {
                viewProgressbar.animateVisibility(true)
            } else {
                viewProgressbar.animateVisibility(false)
            }
        }
    }
}