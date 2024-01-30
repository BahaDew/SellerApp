package com.example.sellerapp.presentation.screens.add_product

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.sellerapp.R
import com.example.sellerapp.data.model.ProductData
import com.example.sellerapp.databinding.ScreenAddProductBinding

class AddProductView : Fragment(R.layout.screen_add_product) {
    private var userId: Long = 0
    private val binding: ScreenAddProductBinding by viewBinding(ScreenAddProductBinding::bind)
    private val viewModel = AddProductViewModel()
    private val navController by lazy { findNavController() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        userId = requireArguments().getLong("userId")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnSave.setOnClickListener {
            viewModel.onClickSaveButton(
                ProductData(
                    id = 0L,
                    productName = binding.productName.text.toString(),
                    priceProduct = binding.productPrice.text.toString().toDouble(),
                    advancePayment = binding.advancePayment.text.toString().toDouble(),
                    monthOfRent = binding.paymentMonth.text.toString().toInt(),
                    comment = binding.comment.text.toString(),
                    userId = userId,
                    checkPay = 0.0,
                    startDate = System.currentTimeMillis()
                )
            )
            navController.popBackStack()
        }
        binding.imageView.setOnClickListener {
            navController.popBackStack()
        }
    }
}