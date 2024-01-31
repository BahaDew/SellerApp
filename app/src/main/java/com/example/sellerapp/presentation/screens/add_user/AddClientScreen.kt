package com.example.sellerapp.presentation.screens.add_user

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.ImageView
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
    private var firstNameValid = false
    private var lastNameValid = false
    private var ageValid = false
    private var phoneNumberValid = false
    private var productNameValid = false
    private var productPriceValid = false
    private var advancePaymentValid = false
    private var monthValid = false
    private var isReadyToSave =  false
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
        initMonth()
    }

    private fun initView() {

        binding.imageView.setOnClickListener {
            findNavController().navigateUp()
        }
        Log.d("TAG", "initView: $firstNameValid $lastNameValid $ageValid")
        if (
            firstNameValid &&
            lastNameValid &&
            ageValid &&
            phoneNumberValid &&
            productNameValid &&
            productPriceValid &&
            advancePaymentValid
        ){
            binding.btnSave.isEnabled = true
        }

        binding.btnSave.setOnClickListener{
//           if(!firstNameValid){
//               println("Iltimos ismingizni qayta kiriting.")
//               return@setOnClickListener
//           }
//            else if (!lastNameValid){
//               println("Iltimos familiyangizni qayta kiriting.")
//               return@setOnClickListener
//            }
//
//           else if (!phoneNumberValid){
//               println("Iltimos telefon raqamingizni qayta kiriting.")
//               return@setOnClickListener
//            }
//
//           else if (!productNameValid){
//               println("Iltimos mahsulot nomini qayta kiriting.")
//               return@setOnClickListener
//            }
//
//           else if (!productPriceValid){
//               println("Iltimos mahsulot narxini qayta kiriting.")
//               return@setOnClickListener
//            }
//
//           else if (!advancePaymentValid){
//               println("Iltimos boshlangich tulov summasini qayta kiriti0ng.")
//               return@setOnClickListener
//            }
//
//           else if (!monthValid){
//               println("Iltimos tulov muddatini qayta kiriting qayta kiriting.")
//               return@setOnClickListener
//            }


     //       else {
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
                    monthOfRent = binding.paymentMonth.text.toString().toInt(),
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
        isReadyToSave = false
        binding.firstname.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val latinPattern = Pattern.compile("[a-zA-Z]+")
                val inputText = s?.toString() ?: ""

                if (!latinPattern.matcher(inputText).matches()) {
                    binding.firstname.error = "Only Latin letters are allowed."
                    isReadyToSave = false
                } else {
                    binding.firstname.error = null
                    isReadyToSave = true
                }

                if(s.toString().length <= 2){
                    binding.firstname.error = "Ismingiz kamida 3 harfdan iborat bo'lishi kerak!!!"
                    isReadyToSave = false
                }
                else {
                    binding.firstname.error  = null
                }

                if(latinPattern.matcher(inputText).matches() && s.toString().length > 2) firstNameValid = true

            }

            override fun afterTextChanged(s: Editable?) {
            }

        })
    }
    private fun initLastName(){
        lastNameValid = false
        binding.lastname.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    val latinPattern = Pattern.compile("[a-zA-Z]+")
                    val inputText = s?.toString() ?: ""
                    lastNameValid = true

                    if (!latinPattern.matcher(inputText).matches()) {
                        binding.lastname.error = "Only Latin letters are allowed."
                        lastNameValid = false
                    } else {
                        // Clear the error if the input is valid
                        binding.lastname.error = null
                    }


                    if(s.toString().length <= 4){
                        binding.lastname.error = "Familiyangiz kamida 5 harfdan iborat bo'lishi kerak!!!"
                    }
                if(latinPattern.matcher(inputText).matches() && s.toString().length > 4 )lastNameValid = true
            }

            override fun afterTextChanged(s: Editable?) {

            }

        })
    }
    private fun initAge(){
        ageValid = false
        binding.age.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                if((!s.toString().equals("")) &&!(s.toString().toInt() >= 16)){
                    binding.age.error = "Yoshingiz 16 yoshdan yuqori bulishi lozim!!!"
                }
                else {
                    ageValid = true
                    binding.age.error = null
                }

            }

            override fun afterTextChanged(s: Editable?) {

            }

        })
    }
    private fun initPhoneNumber(){
        phoneNumberValid = false
        binding.phoneText.addTextChangedListener(object :TextWatcher{

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(s.toString().length < 9 ){
                    binding.phoneText.error = "Bunday raqam mavjud emas"
                }
                else {
                    phoneNumberValid = true
                    binding.phoneText.error = null
                }
            }

            override fun afterTextChanged(s: Editable?) {

            }

        })
    }
    private fun initProductName(){
        productNameValid = false
        binding.productName.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(s.toString().length < 2){
                    binding.productName.error = "Bunday mahsulot mavjud emas"
                }
                else {
                    productNameValid = true
                    binding.productName.error = null
                }
            }

            override fun afterTextChanged(s: Editable?) {

            }

        })
    }
    private fun initProductPrice(){
        productPriceValid = false
        binding.productPrice.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                productPriceValid = false
                if(s.toString().equals("")){
                    binding.productPrice.error = "Iltimos mahsulot narxini kiriting."
                }
                else {
                    productPriceValid = true
                    binding.productPrice.error = null
                }
            }

            override fun afterTextChanged(s: Editable?) {

            }

        })
    }
    private fun initAdmancePayment(){
        advancePaymentValid = false
        binding.advancePayment.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                advancePaymentValid = false
                if(s.toString().equals("")){
                    binding.advancePayment.error = "Iltimos boshlangish tulov narxini kiriting."
                }
                else {
                    advancePaymentValid = true
                    binding.advancePayment.error = null
                }
            }

            override fun afterTextChanged(s: Editable?) {

            }

        })
    }
    private fun initMonth(){
        binding.paymentMonth.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                monthValid = false
                if((!s.toString().equals("")) && s.toString().toInt() < 3) {
                    binding.paymentMonth.error = "Bulib tulash muddati minimal 3 oy."
                }
                else if ((!s.toString().equals("")) && s.toString().toInt() > 12){
                    binding.paymentMonth.error = "Bulib tulash muddati maksimal 12 oy"
                }
                else{
                    monthValid = true
                    binding.paymentMonth.error = null
                }
            }

            override fun afterTextChanged(s: Editable?) {

            }

        })
    }
}