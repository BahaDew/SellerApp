package com.example.sellerapp.presentation.screens.addUser

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
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
import java.util.regex.Pattern

class AddClientScreen() : Fragment(R.layout.dialog_add_client) {
    private val binding by viewBinding(DialogAddClientBinding::bind)
    private var saveButtonListener: ((UserData)-> Unit)? = null
    private val viewModel = EditClientViewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("TAG", "onViewCreated ga tushdi")
        binding.appBarText.text = "Add Client Data"
        initView()
        initDialogListeners()
        initLastNameError()
        initProductPrice()

    }

    private fun initDialogListeners() {
        binding.firstname.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                val latinPattern = Pattern.compile("[a-zA-Z]+")
                val inputText = s?.toString() ?: ""

                if (!latinPattern.matcher(inputText).matches()) {
                    binding.firstname.error = "Only Latin letters are allowed."
                } else {
                    // Clear the error if the input is valid
                    binding.firstname.error = null
                }


                if(s.toString().length <= 2){
                    binding.firstname.error = "Ismingiz kamida 3 harfdan iborat bo'lishi kerak!!!"
                }

            }

            override fun afterTextChanged(s: Editable?) {

            }

        })
    }

    private fun initLastNameError(){
        binding.lastname.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val latinPattern = Pattern.compile("[a-zA-Z]+")
                val inputText = s?.toString() ?: ""

                if (!latinPattern.matcher(inputText).matches()) {
                    binding.lastname.error = "Only Latin letters are allowed."
                } else {
                    // Clear the error if the input is valid
                    binding.lastname.error = null
                }

                if(s.toString().length <= 2){
                    binding.lastname.error = "\n" +
                            "Must be at least 5 letters!!!"
                }
            }

            override fun afterTextChanged(s: Editable?) {

            }

        })
    }
    private fun initProductPrice(){
        binding.advancePayment.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                var summ = s.toString().toLong()
                if(summ < 100_000){
                    binding.productPrice.error = "The price of the product must not be lower than 100,000 summ :)"
                }
            }

            override fun afterTextChanged(s: Editable?) {
            }

        })
    }



    private fun initView() {

        binding.imageView.setOnClickListener {
            findNavController().navigateUp()
        }

      /*  binding.btnSave.setOnClickListener{
            val userData = UserData(
                id = 0L,
                binding.firstname.text.toString(),
                binding.lastname.text.toString(),
              //  binding.number.text.toString(),
                //binding.p"",
                binding.productName.text.toString(),
               // binding.productPrice.text.toString(),
                binding.advancePayment.text.toString(),
                binding.paymentMonth.text.toString(),
                binding.comment.text.toString()
            )
            Log.d("ttt", "save bosildi")
            viewModel.addClient(userData)
           popBackStack()
        }*/
    }
   /* fun setOnSaveButtonListener(block:((UserData)-> Unit)) {
        this.saveButtonListener = block
    }*/
}