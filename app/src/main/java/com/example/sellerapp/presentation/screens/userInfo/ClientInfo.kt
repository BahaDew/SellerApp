package com.example.sellerapp.presentation.screens.userInfo

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.sellerapp.R
import com.example.sellerapp.databinding.ScreenInfoBinding
import com.example.sellerapp.presentation.adapters.LifeTimeAdapter

class ClientInfo : Fragment(R.layout.screen_info) {
    private val binding by viewBinding(ScreenInfoBinding::bind)
    private val adapter by lazy(LazyThreadSafetyMode.NONE) { LifeTimeAdapter() }
    private var userId : Long = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        userId = requireArguments().getLong("userId", 0)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recycler.adapter = adapter
        binding.recycler.layoutManager = LinearLayoutManager(requireContext())
        binding.fullName.text = userId.toString()

    }
}