package com.fuad.mywasteappchanneling.ui.pengambilan

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.fuad.mywasteappchanneling.databinding.ActivityPengambilanBinding
import com.fuad.mywasteappchanneling.ui.main.MainActivity

class PengambilanActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPengambilanBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPengambilanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnJemput.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}