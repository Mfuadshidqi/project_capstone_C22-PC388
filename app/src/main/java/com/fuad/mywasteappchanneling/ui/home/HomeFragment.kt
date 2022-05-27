package com.fuad.mywasteappchanneling.ui.home

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fuad.mywasteappchanneling.R
import com.fuad.mywasteappchanneling.adapter.JenisSampah
import com.fuad.mywasteappchanneling.adapter.ListWasteAdapter
import com.fuad.mywasteappchanneling.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private lateinit var rvJenisSampah : RecyclerView


    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
//        val homeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        showRecyclerList()
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

//        listWasteAdapter.setOnItemClickCallback(object : ListWasteAdapter.OnItemClickCallback {
//            override fun onItemClicked(data: JenisSampah) {
//                showSelectedWaste(data)
//            }
//        })
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}