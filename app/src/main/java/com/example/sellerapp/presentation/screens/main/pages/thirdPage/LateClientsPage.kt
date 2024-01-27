package com.example.sellerapp.presentation.screens.main.pages.thirdPage

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.sellerapp.R
import com.example.sellerapp.databinding.PageThirdBinding
import com.example.sellerapp.databinding.ScreenMainBinding

class LateClientsPage : Fragment(R.layout.page_third) {
    private val binding by viewBinding(PageThirdBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.placeholder.visibility = View.VISIBLE
    }
}