package com.example.sellerapp.presentation.screens.add_user

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.sellerapp.R
import com.example.sellerapp.data.model.ProductData
import com.example.sellerapp.data.model.UserData
import com.example.sellerapp.databinding.ScreenAddClientBinding
import java.util.regex.Pattern

class AddClientScreen : Fragment(R.layout.screen_add_client) {
    private val binding by viewBinding(ScreenAddClientBinding::bind)
    private val viewModel = AddClientViewModel()
    private var saveClicked: Boolean = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
       initFirstName()
        initLastName()
        initAge()
        initPhoneNumber()
        initProductName()
        initProductPrice()
        initAdmancePayment()
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
    private fun initFirstName(){
        binding.firstname.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val latinPattern = Pattern.compile("[a-zA-Z]+")
                val inputText = s?.toString() ?: ""
                saveClicked = false

                if (!latinPattern.matcher(inputText).matches()) {
                    binding.firstname.error = "Only Latin letters are allowed."
                } else {
                    // Clear the error if the input is valid
                    binding.firstname.error = null
                }


                if(s.toString().length <= 2){
                    binding.firstname.error = "Ismingiz kamida 3 harfdan iborat bo'lishi kerak!!!"
                }
                if(latinPattern.matcher(inputText).matches() && s.toString().length > 2) saveClicked = true

            }

            override fun afterTextChanged(s: Editable?) {
            }

        })
    }
    private fun initLastName(){
        binding.lastname.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    val latinPattern = Pattern.compile("[a-zA-Z]+")
                    val inputText = s?.toString() ?: ""
                    saveClicked = true

                    if (!latinPattern.matcher(inputText).matches()) {
                        binding.lastname.error = "Only Latin letters are allowed."
                        saveClicked = false
                    } else {
                        // Clear the error if the input is valid
                        binding.lastname.error = null
                    }


                    if(s.toString().length <= 4){
                        binding.lastname.error = "Familiyangiz kamida 5 harfdan iborat bo'lishi kerak!!!"
                    }
                if(latinPattern.matcher(inputText).matches() && s.toString().length > 4 )saveClicked = true
            }

            override fun afterTextChanged(s: Editable?) {

            }

        })
    }
    private fun initAge(){
        binding.age.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                saveClicked = true
                if(!(s.toString().toInt() >= 16)){
                    binding.age.error = "Yoshingiz 16 yoshdan yuqori bulishi lozim!!!"
                }
                else {
                    saveClicked = true
                    binding.age.error = null
                }

            }

            override fun afterTextChanged(s: Editable?) {

            }

        })
    }
    private fun initPhoneNumber(){
        saveClicked = false
        binding.phoneText.addTextChangedListener(object :TextWatcher{

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(s.toString().length < 9){
                    binding.phoneText.error = "Bunday raqam mavjud emas"
                }
                else {
                    saveClicked = true
                    binding.phoneText.error = null
                }
            }

            override fun afterTextChanged(s: Editable?) {

            }

        })
    }
    private fun initProductName(){
        saveClicked = false
        binding.productName.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(s.toString().length < 2){
                    binding.productName.error = "Bunday mahsulot mavjud emas"
                }
                else {
                    saveClicked = true
                    binding.productName.error = null
                }
            }

            override fun afterTextChanged(s: Editable?) {

            }

        })
    }
    private fun initProductPrice(){
        binding.productPrice.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                saveClicked = false
                if(s.toString().equals("")){
                    binding.productPrice.error = "Iltimos mahsulot narxini kiriting."
                }
                else {
                    saveClicked = true
                    binding.productPrice.error = null
                }
            }

            override fun afterTextChanged(s: Editable?) {

            }

        })
    }
    private fun initAdmancePayment(){
        binding.advancePayment.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                saveClicked = false
                if(s.toString().equals("")){
                    binding.advancePayment.error = "Iltimos boshlangish tulov narxini kiriting."
                }
                else {
                    saveClicked = true
                    binding.advancePayment.error = null
                }
            }

            override fun afterTextChanged(s: Editable?) {

            }

        })
    }
    private fun savedClikedd(){

    }
}