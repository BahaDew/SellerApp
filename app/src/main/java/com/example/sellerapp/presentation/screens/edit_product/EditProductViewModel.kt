package com.example.sellerapp.presentation.screens.edit_product

import androidx.lifecycle.MutableLiveData
import com.example.sellerapp.data.model.ProductData

class EditProductViewModel {
    private val model = EditProductModel()
    val productLD = MutableLiveData<ProductData>()
    fun getProductById(productId : Long) {
        productLD.value = model.getProductById(productId)
    }
    fun onClickSaveButton(productData: ProductData) {
        val product = model.getProductById(productId = productData.id)
        model.updateProduct(productData.copy(
            userId = product.userId,
            checkPay = product.checkPay,
            startDate = product.startDate
        ))
    }
}