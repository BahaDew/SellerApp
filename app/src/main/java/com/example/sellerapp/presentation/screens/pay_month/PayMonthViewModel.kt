package com.example.sellerapp.presentation.screens.pay_month

import androidx.lifecycle.MutableLiveData
import com.example.sellerapp.data.model.ProductData

class PayMonthViewModel {
    private val model = PayMonthModel()
    val productDL = MutableLiveData<ProductData>()
    fun getProduct(id : Long) {
        productDL.value = model.getProductById(id)
    }
    fun updateProduct(productId : Long, checked : Int, summa : Double) {
        val productData = model.getProductById(productId)
        val checkPay = productData.checkPay
        val newProduct = productData.copy(checkPay = if(checked == 1) checkPay + summa else checkPay - summa)
        model.updateProduct(newProduct)
    }
}