package com.example.sellerapp.presentation.screens.main.pages.firstPage

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.sellerapp.R
import com.example.sellerapp.databinding.PageFirstBinding
import com.example.sellerapp.databinding.ScreenMainBinding
import com.example.sellerapp.presentation.screens.addUser.AddClientScreen

class HomePage : Fragment(R.layout.page_first) {
    private val binding by viewBinding(PageFirstBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {


        binding.btnAdd.setOnClickListener {
            AddClientScreen().saveButtonListener = {

            }
            findNavController().navigate(R.id.addClientScreen)
        }
    }
}