package com.fuad.mywasteappchanneling.ui.scanresult

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.fuad.mywasteappchanneling.adapter.Waste
import com.fuad.mywasteappchanneling.databinding.ActivityScanResultBinding
import com.fuad.mywasteappchanneling.ui.penjemputan.PenjemputanActivity

class ScanResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivityScanResultBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScanResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        val waste = intent.getParcelableExtra<Waste>(EXTRA_WASTE) as Waste

        binding.tvHasilScan.text = waste.name
        Glide.with(this)
            .load(waste.photo)
            .into(binding.imageView)

        binding.btnLanjutkan.setOnClickListener{
            showSelectedWaste(waste)
        }

    }

    private fun showSelectedWaste(data: Waste) {
        val moveWithObjectIntent = Intent(this, PenjemputanActivity::class.java)
        moveWithObjectIntent.putExtra(PenjemputanActivity.EXTRA_DATA, data.name)
        startActivity(moveWithObjectIntent)
        finish()
    }

    companion object {
        const val EXTRA_WASTE = "extra_waste"
    }
}