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

        val jenisSampah = intent.getParcelableExtra<JenisSampah>(EXTRA_DATA) as JenisSampah
        Log.d("JENIS", jenisSampah.name)
            binding.tvJenisSampah.text = jenisSampah.name
    }

    companion object {
        const val EXTRA_DATA = "extra_data"
    }
}