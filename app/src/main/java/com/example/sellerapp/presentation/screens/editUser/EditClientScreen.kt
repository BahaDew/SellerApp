package com.example.sellerapp.presentation.screens.editUser

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.sellerapp.R
import com.example.sellerapp.data.model.UserData
import com.example.sellerapp.databinding.DialogAddClientBinding
import com.example.sellerapp.utils.popBackStack

class EditClientScreen : Fragment(R.layout.dialog_add_client) {
    private val binding by viewBinding(DialogAddClientBinding::bind)
    private var saveButtonListener: ((UserData)-> Unit)? = null
    private val viewModel = EditClientViewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//         Pair("firstName", user.firstName),
//                    Pair("lastName", user.secondName),
//                    Pair("number", user.phoneNumber),
//                    Pair("productName", user.productName),
//                    Pair("productPrice", user.productPrice),
//                    Pair("advance_payment", user.advance_payment),
//                    Pair("monthOfRent", user.monthOfRent),
//                    Pair("comment", user.comment),
        val lastName = requireArguments().getString("lastName")
        val number = requireArguments().getString("number")
        val firstName = requireArguments().getString("firstName")
        val productName = requireArguments().getString("productName")
        val productPrice = requireArguments().getString("productPrice")
        val advance_payment = requireArguments().getString("advance_payment")
        val monthOfRent = requireArguments().getString("monthOfRent")
        val comment = requireArguments().getString("comment")
        binding.firstname.setText(firstName)
        binding.lastname.setText(lastName)
        binding.number.setText(number)
        binding.productName.setText(productName)
        binding.productPrice.setText(productPrice)
        binding.paymentMonth.setText(monthOfRent)
        binding.advancePayment.setText(advance_payment)
        binding.comment.setText(comment)
        initView()
    }

    private fun initView() {
        binding.imageView.setOnClickListener {
            popBackStack()
        }

        binding.btnSave.setOnClickListener{
            val userData = UserData(
                id = 0L,
                binding.firstname.text.toString(),
                binding.lastname.text.toString(),
                binding.number.text.toString(),
                binding.productName.text.toString(),
                binding.productPrice.text.toString(),
                binding.advancePayment.text.toString(),
                binding.paymentMonth.text.toString(),
                binding.comment.text.toString()
            )
            viewModel.editClient(userData)
            popBackStack()
        }
    }
    fun setOnSaveButtonListener(block:((UserData)-> Unit)) {
        this.saveButtonListener = block
    }
}