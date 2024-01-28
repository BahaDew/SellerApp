package com.example.sellerapp.presentation.screens.addUser

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.room.InvalidationTracker
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.sellerapp.R
import com.example.sellerapp.data.model.ProductData
import com.example.sellerapp.data.model.UserData
import com.example.sellerapp.databinding.DialogAddClientBinding
import com.example.sellerapp.presentation.screens.editUser.EditClientViewModel
import com.example.sellerapp.utils.popBackStack

class AddClientScreen() : Fragment(R.layout.dialog_add_client) {
    private val binding by viewBinding(DialogAddClientBinding::bind)
    private val viewModel = AddClientViewModel()

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.appBarText.text = "Add Client Data"
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
                priceProduct = binding.productPrice.text.toString().toInt(),
                advance_payment = binding.advancePayment.text.toString(),
                monthOfRent =  binding.paymentMonth.text.toString(),
                comment = binding.comment.text.toString(),
                checkPay = 0,
                userId = 0L,
                startDate = System.currentTimeMillis()
            )
            viewModel.addClientAndProduct(userData, productData)
            findNavController().navigateUp()
        }
    }
}