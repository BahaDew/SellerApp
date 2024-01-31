package com.example.sellerapp.presentation.screens.client_Info

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.sellerapp.R
import com.example.sellerapp.data.model.ProductData
import com.example.sellerapp.data.model.UserData
import com.example.sellerapp.databinding.ScreenInfoClientBinding
import com.example.sellerapp.presentation.adapters.ProductListAdapter

class ClientInfo : Fragment(R.layout.screen_info_client) {
    private val binding by viewBinding(ScreenInfoClientBinding::bind)
    private var userId: Long = 0
    private val adapter = ProductListAdapter()
    private val viewModel = ClientInfoViewModel()
    private val navController by lazy { findNavController() }
    private val callObserver = Observer<UserData> { makeCall(it) }
    private val dialog by lazy { Dialog(requireContext()) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        userId = requireArguments().getLong("userId", 0)
    }

    @SuppressLint("SetTextI18n", "FragmentLiveDataObserve")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getUserById(userId)
        viewModel.getProductByUserId(userId)
        viewModel.userLD.observe(viewLifecycleOwner) {
            binding.firstName.text = it.firstName
            binding.lastname.text = it.secondName
            binding.age.text = "${it.age} yosh"
            binding.phoneNumber.text = it.phoneNumber
        }
        viewModel.productsLD.observe(viewLifecycleOwner) {
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
        adapter.setLongClickListener {
            openEditDeleteDialog(it)
        }
    }

    private fun makeCall(user: UserData) {
        try {
            val phone = user.phoneNumber.replace(" ", "")
            val callIntent = Intent(Intent.ACTION_CALL)
            callIntent.data = Uri.parse("tel:$phone")
            startActivity(callIntent)
        } catch (e: Exception) {
            Log.d("TTT", e.message.toString())
        }
    }

    private fun openEditDeleteDialog(productData: ProductData) {
        dialog.setContentView(R.layout.dialog_choose_edit_delete)
        dialog.findViewById<View>(R.id.btn_delete).setOnClickListener {
            if (dialog.isShowing) {
                viewModel.deleteProduct(productData)
                viewModel.getProductByUserId(userId)
                dialog.dismiss()
            }
        }
        dialog.findViewById<View>(R.id.btn_edit).setOnClickListener {
            if (dialog.isShowing) {
                val bundle = Bundle()
                bundle.putLong("productId", productData.id)
                navController.navigate(R.id.editProductView, bundle)
                dialog.dismiss()
            }
        }
        dialog.show()
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        dialog.window?.attributes?.windowAnimations = R.style.DialogAnimation
        dialog.window?.setGravity(Gravity.BOTTOM)
    }
}