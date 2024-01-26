package com.example.sellerapp.presentation.screens.addUser

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.sellerapp.R
import com.example.sellerapp.data.model.UserData
import com.example.sellerapp.databinding.DialogAddClientBinding
import com.example.sellerapp.presentation.screens.editUser.EditClientViewModel
import com.example.sellerapp.utils.popBackStack

class AddClientScreen() : Fragment(R.layout.dialog_add_client) {
    private val binding by viewBinding(DialogAddClientBinding::bind)
    private var saveButtonListener: ((UserData)-> Unit)? = null
    private val viewModel = EditClientViewModel()

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("TAG", "onViewCreated ga tushdi")
        binding.appBarText.text = "Add Client Data"
        initView()
    }

    private fun initView() {

        binding.imageView.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.btnSave.setOnClickListener{
//            val userData = UserData(
//                id = 0L,
//                binding.firstname.text.toString(),
//                binding.lastname.text.toString(),
////                binding.number.text.toString(),
//                "",
//                binding.productName.text.toString(),
//                binding.productPrice.text.toString(),
//                binding.advancePayment.text.toString(),
//                binding.paymentMonth.text.toString(),
//                binding.comment.text.toString()
//            )
//            Log.d("ttt", "save bosildi")
//            viewModel.addClient(userData)
           popBackStack()
        }
    }
    fun setOnSaveButtonListener(block:((UserData)-> Unit)) {
        this.saveButtonListener = block
    }
}