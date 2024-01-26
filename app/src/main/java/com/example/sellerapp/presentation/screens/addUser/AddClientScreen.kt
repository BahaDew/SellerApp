package com.example.sellerapp.presentation.screens.addUser

import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.sellerapp.R
import com.example.sellerapp.data.model.UserData
import com.example.sellerapp.databinding.DialogAddClientBinding
import com.example.sellerapp.databinding.ScreenSplashBinding
import com.example.sellerapp.presentation.screens.main.MainScreen
import com.example.sellerapp.presentation.screens.main.pages.firstPage.HomePage
import com.example.sellerapp.utils.popBackStack
import com.example.sellerapp.utils.replaceScreenWithoutSave

class AddClientScreen() : Fragment(R.layout.dialog_add_client) {
    private val binding by viewBinding(DialogAddClientBinding::bind)
    private var saveButtonListener: ((UserData)-> Unit)? = null
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
            val homePage = MainScreen()
            homePage.apply {
                arguments = bundleOf(
                    Pair("1", 0),
                    Pair("2", binding.firstname.text.toString()),
                    Pair("3", binding.lastname.text.toString()),
                    Pair("4", binding.number.text.toString()),
                    Pair("5", binding.productName.text.toString()),
                    Pair("6", binding.productPrice.text.toString()),
                    Pair("7", binding.advancePayment.text.toString()),
                    Pair("8", binding.paymentMonth.text.toString()),
                    Pair("9", binding.comment.text.toString()),
                )
            }
            replaceScreenWithoutSave(MainScreen())

//            val userData = UserData(
//                id = 0L,
//                binding.firstname.text.toString(),
//                binding.lastname.text.toString(),
//                binding.number.text.toString(),
//                binding.productName.text.toString(),
//                binding.productPrice.text.toString(),
//                binding.advancePayment.text.toString(),
//                binding.paymentMonth.text.toString(),
//                binding.comment.text.toString()
//            )
//            val direction = AddClientScreenDirections.actionAddClientScreenToHomePage()
//            findNavController().navigate(direction)
        }
    }
    fun setOnSaveButtonListener(block:((UserData)-> Unit)) {
        this.saveButtonListener = block
    }
}