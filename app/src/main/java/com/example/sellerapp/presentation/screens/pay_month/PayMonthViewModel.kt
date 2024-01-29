package com.example.sellerapp.presentation.screens.pay_month

import androidx.lifecycle.MutableLiveData
import com.example.sellerapp.data.model.ProductData

class PayMonthViewModel {
    private val model = PayMonthModel()
    val productDL = MutableLiveData<ProductData>()
    fun getProduct(id : Long) {
        productDL.value = model.getProductById(id)
    }
}