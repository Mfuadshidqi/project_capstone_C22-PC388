package com.fuad.mywasteappchanneling.ui.profil

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.fuad.mywasteappchanneling.databinding.FragmentProfilBinding
import com.fuad.mywasteappchanneling.ui.factory.UserViewModelFactory
import com.fuad.mywasteappchanneling.ui.login.LoginActivity

class ProfilFragment : Fragment() {

    private var _binding: FragmentProfilBinding? = null
    private lateinit var profilViewModel: ProfilViewModel
    private lateinit var token: String

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfilBinding.inflate(inflater, container, false)
        val root: View = binding.root
        binding.cardLogout.setOnClickListener{profilViewModel.logout()}
        setupViewModel()
        return root
    }

    private fun setupViewModel(){
        val factory: UserViewModelFactory = UserViewModelFactory.getInstance(requireActivity())
        profilViewModel = ViewModelProvider(
            this,
            factory
        )[ProfilViewModel::class.java]

        profilViewModel.isLogin().observe(requireActivity()) {
            if (!it) {
                val intent = Intent (activity,LoginActivity::class.java)
                activity?.startActivity(intent)
                onDestroy()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}