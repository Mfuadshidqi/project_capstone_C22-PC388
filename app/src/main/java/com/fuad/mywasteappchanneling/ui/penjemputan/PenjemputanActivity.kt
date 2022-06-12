package com.fuad.mywasteappchanneling.ui.penjemputan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.fuad.mywasteappchanneling.R
import com.fuad.mywasteappchanneling.databinding.ActivityPenjemputanBinding
import com.fuad.mywasteappchanneling.ui.factory.UserViewModelFactory
import com.fuad.mywasteappchanneling.data.Result
import com.fuad.mywasteappchanneling.ui.pengambilan.PengambilanActivity
import com.fuad.mywasteappchanneling.utils.animateVisibility

class PenjemputanActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPenjemputanBinding
    private lateinit var penjemputanViewModel :PenjemputanViewModel
    private var userId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityPenjemputanBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        val jenisSampah = intent.getStringExtra(EXTRA_DATA)
        val harga = intent.getStringExtra(EXTRA_HARGA)
//        Log.d("JENIS", jenisSampah.toString())
        binding.tvJenisSampah.text = jenisSampah
        binding.harga.text = harga

        binding.rbCod.setOnClickListener {
            val waste = getData()
            binding.harga.text = waste.harga.toString()
        }
        setupViewModel()
        setupAction()
    }

    private fun setupViewModel() {
        val factory: UserViewModelFactory = UserViewModelFactory.getInstance(this)
        penjemputanViewModel = ViewModelProvider(this, factory)[PenjemputanViewModel::class.java]

        penjemputanViewModel.getUserId().observe(this){
            userId = it
        }
    }

    fun getData(): Waste {
        var sampah = 1
        var idSampah = 1
        when (binding.tvJenisSampah.text){
            "PLASTIK" -> {
                sampah = 5000
                idSampah = 1
            }
            "KAIN" -> {
                sampah = 5000
                idSampah = 2
            }
            "B3" -> {
                sampah = 10000
                idSampah = 3
            }
            "MAKANAN" -> {
                sampah = 5000
                idSampah = 4
            }
            "ELEKTRONIK" -> {
                sampah = 10000
                idSampah = 5
            }
            "RESIDU" -> {
                sampah = 15000
                idSampah = 6
            }
            "KERTAS/KARTON" -> {
                sampah = 5000
                idSampah = 5
            }
            "KACA" -> {
                sampah = 10000
                idSampah = 6
            }
            "LOGAM" -> {
                sampah = 10000
                idSampah = 7
            }
        }
        val berat = when(binding.edtBerat.text.toString()) {
            "" -> 0
            else -> binding.edtBerat.text.toString().toInt()
        }
        var idJasa = 1
        var jasa = 1
        when(binding.rgJasa.checkedRadioButtonId){
            R.id.rb_motor -> {
                idJasa = 1
                jasa = 5000
            }
            R.id.rb_truck -> {
                idJasa = 2
                jasa = 3000
            }
        }
        val harga = jasa + berat * sampah

        return Waste(
            sampah, idSampah, berat, idJasa, jasa, harga
        )
    }

    data class Waste(
        val sampah: Int,
        val idSampah: Int,
        val berat: Int,
        val idJasa: Int,
        val jasa: Int,
        val harga: Int
    )

    private fun setupAction() {
        binding.btnJemput.setOnClickListener {
            val waste = getData()
            when {
                waste.berat == 0 -> {
                    binding.edtBerat.error = resources.getString(R.string.message_validation, "berat")
                }
                !binding.rbMotor.isChecked && !binding.rbTruck.isChecked -> {
                    binding.rbMotor.error = resources.getString(R.string.message_validation, "pilih jasa")
                }
                !binding.rbCod.isChecked -> {
                    binding.rbCod.error = resources.getString(R.string.message_validation, "pilih metode pembayaran")
                }
                else -> {
//                    Log.d("TES", "$harga, $berat, $idSampah, $idJasa, $userId")
                    penjemputanViewModel.addTransaction(waste.harga, waste.berat, waste.idSampah, waste.idJasa, userId).observe(this){ result ->
                        if (result != null){
                            when(result) {
                                is Result.Loading -> {
                                    showLoading(true)
                                }
                                is Result.Success -> {
                                    showLoading(false)
                                    Toast.makeText(
                                        this,
                                        "Transaksi Berhasil !",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    startActivity(Intent(this, PengambilanActivity:: class.java))
                                    finish()
                                }
                                is Result.Error -> {
                                    showLoading(false)
                                    Toast.makeText(
                                        this,
                                        result.error,
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }
                        }
                    }
                }
            }
        }

//        binding.tvDisiniLog.setOnClickListener{
//            val intent = Intent(this, LoginActivity::class.java)
//            startActivity(intent)
//        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.apply {
            edtBerat.isEnabled = !isLoading

            if (isLoading) {
                viewProgressbar.animateVisibility(true)
            } else {
                viewProgressbar.animateVisibility(false)
            }
        }
    }

    companion object {
        const val EXTRA_DATA = "extra_data"
        const val EXTRA_HARGA = "extra_harga"
    }
}