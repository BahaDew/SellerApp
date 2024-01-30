package com.example.sellerapp.presentation.screens.userInfo

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.sellerapp.R
import com.example.sellerapp.data.model.UserData
import com.example.sellerapp.databinding.ScreenInfoBinding
import com.example.sellerapp.presentation.adapters.ProductListAdapter

class ClientInfo : Fragment(R.layout.screen_info) {
    private val binding by viewBinding(ScreenInfoBinding::bind)
    private var userId: Long = 0
    private val adapter = ProductListAdapter()
    private val viewModel = ClientInfoViewModel()
    private val navController by lazy { findNavController() }
    private val callObserver = Observer<UserData> {makeCall(it)}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        userId = requireArguments().getLong("userId", 0)
    }

    @SuppressLint("SetTextI18n", "FragmentLiveDataObserve")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getUserById(userId)
        viewModel.getProductById(userId)
        viewModel.userLD.observe(viewLifecycleOwner) {
            binding.firstName.text = it.firstName
            binding.lastname.text = it.secondName
            binding.age.text = "${it.age} yosh"
            binding.phoneNumber.text = it.phoneNumber
        }
        viewModel.productLD.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
        binding.imageView.setOnClickListener {
            navController.navigateUp()
        }
        binding.btnAdd.setOnClickListener {
            val bundle = Bundle()
            bundle.putLong("userId", userId)
            navController.navigate(R.id.addProductView, bundle)
        }
        binding.recycler.adapter = adapter
        binding.recycler.layoutManager = LinearLayoutManager(requireContext())
        adapter.setOnClickListener {
            val bundle = Bundle()
            bundle.putLong("productId", it.id)
            navController.navigate(R.id.payMonthView, bundle)
        }
        binding.btnCall.setOnClickListener {
            viewModel.onClickCall(userId)
        }
        viewModel.userCallLD.observe(this, callObserver)
    }
    private fun makeCall(user : UserData) {
        try {
            val phone = user.phoneNumber.replace(" ", "")
            val callIntent = Intent(Intent.ACTION_CALL)
            callIntent.data = Uri.parse("tel:$phone")
            startActivity(callIntent)
        } catch (e : Exception) {
            Log.d("TTT", e.message.toString())
        }
    }
}