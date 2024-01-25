package com.example.sellerapp.presentation.screens.addUser

import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.sellerapp.R
import com.example.sellerapp.databinding.DialogAddClientBinding
import com.example.sellerapp.databinding.ScreenSplashBinding

class AddClientScreen() : Fragment(R.layout.dialog_add_client) {
    private val binding by viewBinding(DialogAddClientBinding::bind)
    lateinit var saveButtonListener: (String)-> Unit

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
    }

    private fun initView() {
        binding.imageView.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.btnSave.setOnClickListener{
            //saveButtonListener.invoke(binding.editTextClientName.text.toString())
            findNavController().navigateUp()
        }
    }
}