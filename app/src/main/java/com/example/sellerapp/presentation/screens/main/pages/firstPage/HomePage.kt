package com.example.sellerapp.presentation.screens.main.pages.firstPage

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.sellerapp.R
import com.example.sellerapp.data.model.UserData
import com.example.sellerapp.databinding.PageFirstBinding
import com.example.sellerapp.presentation.screens.addUser.AddClientScreen
import com.example.sellerapp.utils.replaceScreen

class HomePage : Fragment(R.layout.page_first) {
    private val binding by viewBinding(PageFirstBinding::bind)
    private val viewModel = HomeViewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {


        binding.btnAdd.setOnClickListener {
            val addClientScreen = AddClientScreen()
//            addClientScreen.setOnSaveButtonListener {
//                val userData = UserData(
//                    id = 0L,
//                    firstName = it.firstName,
//                    secondName = it.secondName,
//                    phoneNumber = it.phoneNumber,
//                    productName = it.productName,
//                    productPrice = it.productPrice,
//                    advance_payment = it.advance_payment,
//                    monthOfRent = it.monthOfRent,
//                    comment = it.comment
//                )
//                Log.d("TAG", "initView: ${it.firstName}")
//                viewModel.addClient(userData)
//            }
            replaceScreen(AddClientScreen())
            //findNavController().navigate(R.id.addClientScreen)
        }
    }
}