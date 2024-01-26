package com.example.sellerapp.presentation.screens.main.pages.secondPage

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.sellerapp.R
import com.example.sellerapp.databinding.PageSecondBinding
import com.example.sellerapp.databinding.ScreenMainBinding

class PayTodayClientsPage : Fragment(R.layout.page_second) {
    private val binding by viewBinding(PageSecondBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.placeholder.visibility = View.VISIBLE
    }
}