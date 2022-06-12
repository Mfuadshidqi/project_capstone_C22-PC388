package com.fuad.mywasteappchanneling.ui.riwayat

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.load.engine.Resource
import com.fuad.mywasteappchanneling.adapter.JenisSampah
import com.fuad.mywasteappchanneling.adapter.ListRiwayatAdapter
import com.fuad.mywasteappchanneling.adapter.ListWasteAdapter
import com.fuad.mywasteappchanneling.databinding.FragmentRiwayatBinding
import com.fuad.mywasteappchanneling.ui.factory.RiwayatViewModelFactory
import com.fuad.mywasteappchanneling.data.Result
import com.fuad.mywasteappchanneling.ui.factory.UserViewModelFactory
import com.fuad.mywasteappchanneling.ui.login.LoginViewModel
import com.fuad.mywasteappchanneling.ui.main.MainActivity
import com.fuad.mywasteappchanneling.ui.penjemputan.PenjemputanActivity

class RiwayatFragment : Fragment() {

    private var _binding: FragmentRiwayatBinding? = null
    private lateinit var riwayatViewModel : RiwayatViewModel

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentRiwayatBinding.inflate(inflater, container, false)
        val root: View = binding.root
        setupViewModel()
        loadData()
        return root
    }

    private fun setupViewModel() {
        val factory = RiwayatViewModelFactory.getInstance(requireActivity())
        riwayatViewModel = ViewModelProvider(this, factory)[RiwayatViewModel::class.java]
    }

    private fun loadData() {
        val listRiwayatAdapter = ListRiwayatAdapter()
        binding?.rvStories?.adapter = listRiwayatAdapter
        riwayatViewModel.getTransaction().observe(viewLifecycleOwner){riwayat ->
            if (riwayat != null){
                when(riwayat){
                    is Result.Loading -> binding.viewProgressbar.visibility = View.VISIBLE
                    is Result.Success -> {
                        binding.viewProgressbar.visibility = View.GONE
                        listRiwayatAdapter.setData(riwayat.data.values)
                    }
                }

            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}