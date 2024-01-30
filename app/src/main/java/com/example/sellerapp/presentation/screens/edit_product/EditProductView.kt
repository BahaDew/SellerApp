package com.example.sellerapp.presentation.screens.edit_product

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.sellerapp.R
import com.example.sellerapp.data.model.ProductData
import com.example.sellerapp.databinding.ScreenEditProductBinding
import java.text.NumberFormat

class EditProductView : Fragment(R.layout.screen_edit_product) {
    private var productId: Long = 0
    private val binding: ScreenEditProductBinding by viewBinding(ScreenEditProductBinding::bind)
    private val viewModel = EditProductViewModel()
    private val navController by lazy { findNavController() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        productId = requireArguments().getLong("productId")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getProductById(productId)
        viewModel.productLD.observe(viewLifecycleOwner) {
            binding.productName.setText(it.productName)
            binding.productPrice.setText(it.priceProduct.toString())
            binding.advancePayment.setText(it.advancePayment.toString())
            binding.paymentMonth.setText(it.monthOfRent.toString())
            binding.comment.setText(it.comment)
        }
        binding.btnSave.setOnClickListener {
            viewModel.onClickSaveButton(
                ProductData(
                    id = productId,
                    productName = binding.productName.text.toString(),
                    priceProduct = binding.productPrice.text.toString().toDouble(),
                    advancePayment = binding.advancePayment.text.toString().toDouble(),
                    monthOfRent = binding.paymentMonth.text.toString().toInt(),
                    comment = binding.comment.text.toString(),
                    userId = 0,
                    checkPay = 0.0,
                    startDate = 0L
                )
            )
            navController.popBackStack()
        }
        binding.imageView.setOnClickListener {
            navController.popBackStack()
        }
    }
    private fun amountFormat(amount : Double) : String{
        return NumberFormat.getInstance().format(amount)
    }
}