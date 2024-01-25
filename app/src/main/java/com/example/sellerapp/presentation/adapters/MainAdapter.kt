package com.example.sellerapp.presentation.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.sellerapp.presentation.screens.main.pages.firstPage.HomePage
import com.example.sellerapp.presentation.screens.main.pages.secondPage.PayTodayClientsPage
import com.example.sellerapp.presentation.screens.main.pages.thirdPage.LateClientsPage

class MainAdapter(fm: FragmentManager, lifecycle: Lifecycle) : FragmentStateAdapter(fm, lifecycle) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment =
        when (position) {
            0 -> {
                HomePage()
            }

            1 -> {
                PayTodayClientsPage()
            }

            else -> {
                LateClientsPage()
            }
        }

}