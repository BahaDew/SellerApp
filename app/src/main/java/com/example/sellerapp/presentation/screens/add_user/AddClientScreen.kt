package com.example.sellerapp.presentation.screens.add_user

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.sellerapp.R
import com.example.sellerapp.data.model.ProductData
import com.example.sellerapp.data.model.UserData
import com.example.sellerapp.databinding.ScreenAddClientBinding

class AddClientScreen : Fragment(R.layout.screen_add_client) {
    private val binding by viewBinding(ScreenAddClientBinding::bind)
    private val viewModel = AddClientViewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        binding.imageView.setOnClickListener {
            findNavController().navigateUp()
        }
        binding.btnSave.setOnClickListener{
            val userData = UserData(
                id = 0L,
                firstName = binding.firstname.text.toString(),
                secondName = binding.lastname.text.toString(),
                age = binding.age.text.toString().toInt(),
                phoneNumber = binding.phoneText.text.toString()
            )
            val productData = ProductData(
                productName = binding.productName.text.toString(),
                id = 0L,
                priceProduct = binding.productPrice.text.toString().toDouble(),
                advancePayment = binding.advancePayment.text.toString().toDouble(),
                monthOfRent =  binding.paymentMonth.text.toString().toInt(),
                comment = binding.comment.text.toString(),
                checkPay = 0.0,
                userId = 0L,
                startDate = System.currentTimeMillis()
            )
            viewModel.addClientAndProduct(userData, productData)
            findNavController().navigateUp()
        }

    }
}