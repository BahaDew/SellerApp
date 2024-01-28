package com.example.sellerapp.presentation.screens.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.sellerapp.R
import com.example.sellerapp.databinding.ScreenMainBinding
import com.example.sellerapp.presentation.adapters.MainAdapter
import com.google.android.material.tabs.TabLayoutMediator

class MainScreen(): Fragment(R.layout.screen_main) {
    private val binding by viewBinding(ScreenMainBinding::bind)
    private var adapter : MainAdapter? = null
    private val tabItem = arrayListOf("Home", "Today", "Late")

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = MainAdapter(childFragmentManager, lifecycle)
        binding.viewPager.adapter = adapter
        binding.viewPager.isUserInputEnabled = true
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, pos ->
            tab.text = tabItem[pos]
        }.attach()
    }
}