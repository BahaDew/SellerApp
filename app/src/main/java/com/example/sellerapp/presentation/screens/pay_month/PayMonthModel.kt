package com.example.sellerapp.presentation.screens.pay_month

import com.example.sellerapp.data.model.ProductData
import com.example.sellerapp.domain.AppRepositoryImpl

class PayMonthModel {
    private val repo = AppRepositoryImpl.getInstance()
    fun getProductById(id : Long) : ProductData {
        return repo.getProductById(id)
    }
    fun updateProduct(productData: ProductData) {
        repo.updateProduct(productData)
    }
}