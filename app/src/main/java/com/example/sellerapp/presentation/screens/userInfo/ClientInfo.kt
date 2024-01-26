package com.example.sellerapp.presentation.screens.userInfo

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.sellerapp.R
import com.example.sellerapp.databinding.ScreenInfoBinding
import com.example.sellerapp.databinding.ScreenMainBinding
import com.example.sellerapp.presentation.adapters.LifeTimeAdapter

class ClientInfo : Fragment(R.layout.screen_info) {
    private val binding by viewBinding(ScreenInfoBinding::bind)
    private  val adapter = LifeTimeAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recycler.adapter = adapter
        binding.recycler.layoutManager = LinearLayoutManager(requireContext())
    }

}