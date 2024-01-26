package com.example.sellerapp.presentation.screens.main.pages.firstPage

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.sellerapp.R
import com.example.sellerapp.data.model.UserData
import com.example.sellerapp.databinding.PageFirstBinding
import com.example.sellerapp.presentation.adapters.PageFirstAdapter
import com.example.sellerapp.presentation.screens.addUser.AddClientScreen
import com.example.sellerapp.utils.replaceScreen

class HomePage : Fragment(R.layout.page_first) {
    private val binding by viewBinding(PageFirstBinding::bind)
    private val viewModel = HomeViewModel()
    private val adapter by lazy { PageFirstAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        viewModel.transferData.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }




        binding.btnAdd.setOnClickListener {
//            replaceScreen(AddClientScreen())
            findNavController().navigate(R.id.addClientScreen)
        }
    }
}