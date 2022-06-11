package com.fuad.mywasteappchanneling.ui.penjemputan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.fuad.mywasteappchanneling.adapter.JenisSampah
import com.fuad.mywasteappchanneling.databinding.ActivityPenjemputanBinding

class PenjemputanActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPenjemputanBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityPenjemputanBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val jenisSampah = intent.getStringExtra(EXTRA_DATA)
        Log.d("JENIS", jenisSampah.toString())
            binding.tvJenisSampah.text = jenisSampah
    }

    companion object {
        const val EXTRA_DATA = "extra_data"
    }
}