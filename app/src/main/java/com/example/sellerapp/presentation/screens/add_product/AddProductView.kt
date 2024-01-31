package com.example.sellerapp.presentation.screens.add_product

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.sellerapp.R
import com.example.sellerapp.data.model.ProductData
import com.example.sellerapp.databinding.ScreenAddProductBinding

class AddProductView : Fragment(R.layout.screen_add_product) {
    private var userId: Long = 0
    private var monthValid = false
    private val binding: ScreenAddProductBinding by viewBinding(ScreenAddProductBinding::bind)
    private val viewModel = AddProductViewModel()
    private val navController by lazy { findNavController() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        userId = requireArguments().getLong("userId")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(monthValid)binding.btnSave.isEnabled = true
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
        initMonth()
    }
    private fun initMonth(){
        binding.paymentMonth.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                monthValid = false
                if((!s.toString().equals("")) && s.toString().toInt() < 3) {
                    binding.paymentMonth.error = "Bulib tulash muddati minimal 3 oy."
                    binding.btnSave.isEnabled = false
                    monthValid = false
                }
                else if ((!s.toString().equals("")) && s.toString().toInt() > 12){
                    binding.paymentMonth.error = "Bulib tulash muddati maksimal 12 oy"
                    binding.btnSave.isEnabled = false
                    monthValid = false
                }
                else{
                    binding.btnSave.isEnabled = true
                    monthValid = true
                    binding.paymentMonth.error = null

                }
            }

            override fun afterTextChanged(s: Editable?) {

            }

        })
    }
}