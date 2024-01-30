package com.example.sellerapp.presentation.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.sellerapp.presentation.screens.main.pages.firstPage.HomePage
import com.example.sellerapp.presentation.screens.main.pages.secondPage.PayTodayClients
import com.example.sellerapp.presentation.screens.main.pages.thirdPage.LateClients

class MainAdapter(fm: FragmentManager, lifecycle: Lifecycle) : FragmentStateAdapter(fm, lifecycle) {
    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment =
        when (position) {
            0 -> HomePage()
            1 -> PayTodayClients()
            else -> LateClients()
        }
}