package com.fuad.mywasteappchanneling.ui.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.fuad.mywasteappchanneling.R
import com.fuad.mywasteappchanneling.adapter.JenisSampah
import com.fuad.mywasteappchanneling.adapter.ListWasteAdapter
import com.fuad.mywasteappchanneling.databinding.FragmentHomeBinding
import com.fuad.mywasteappchanneling.ui.scanner.ScannerActivity
import com.fuad.mywasteappchanneling.ui.penjemputan.PenjemputanActivity

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
//        val homeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        showRecyclerList()

        binding.buttonPindai.setOnClickListener{
            startActivity(Intent(activity, ScannerActivity :: class.java))
        }
        return binding.root
    }

    private val listWaste: ArrayList<JenisSampah>
        get() {
            val dataJenis = resources.getStringArray(R.array.jenis)
            val dataAvatar = resources.obtainTypedArray(R.array.gambar_sampah)
            val listWaste = ArrayList<JenisSampah>()
            for (i in dataJenis.indices) {
                val jenis = JenisSampah(dataJenis[i], dataAvatar.getResourceId(i,-1) )
                listWaste.add(jenis)
                Log.d("WASTE", dataAvatar.getResourceId(i, -1).toString())
            }
            return listWaste
        }

    private fun showRecyclerList() {

        val listWasteAdapter = ListWasteAdapter(listWaste)
        binding.rvJenis.adapter = listWasteAdapter

        listWasteAdapter.setOnItemClickCallback(object : ListWasteAdapter.OnItemClickCallback {
            override fun onItemClicked(data: JenisSampah) {
                showSelectedWaste(data)
            }
        })
    }

    private fun showSelectedWaste(data: JenisSampah) {
        val moveWithObjectIntent = Intent(activity, PenjemputanActivity::class.java)
        moveWithObjectIntent.putExtra(PenjemputanActivity.EXTRA_DATA, data)
        startActivity(moveWithObjectIntent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}