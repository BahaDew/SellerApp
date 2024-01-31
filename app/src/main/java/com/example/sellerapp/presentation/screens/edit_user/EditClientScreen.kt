package com.example.sellerapp.presentation.screens.edit_user

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.sellerapp.R
import com.example.sellerapp.data.model.UserData
import com.example.sellerapp.databinding.ScreenEditClientBinding

class EditClientScreen : Fragment(R.layout.screen_edit_client) {
    private val binding by viewBinding(ScreenEditClientBinding::bind)
    private val viewModel = EditClientViewModel()
    private val navController by lazy { findNavController() }
    private var userId = 0L
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        userId = requireArguments().getLong("userId")
    }
    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        viewModel.getUserById(userId)
        viewModel.userLD.observe(viewLifecycleOwner) {
            binding.firstname.setText(it.firstName)
            binding.lastname.setText(it.secondName)
            binding.age.setText(it.age.toString())
            binding.phoneText.setText(it.phoneNumber)
        }
        binding.imageView.setOnClickListener {
            navController.navigateUp()
        }
        binding.btnSave.setOnClickListener {
            val userData = UserData(
                id = userId,
                firstName = binding.firstname.text.toString(),
                secondName = binding.lastname.text.toString(),
                age = binding.age.text.toString().toInt(),
                phoneNumber = binding.phoneText.text.toString()
            )
            viewModel.editClient(userData)
            navController.navigateUp()
        }
    }
}