package com.example.sellerapp.presentation.screens.main.pages.secondPage

import com.example.sellerapp.data.model.ProductData
import com.example.sellerapp.data.model.UserData
import com.example.sellerapp.domain.AppRepositoryImpl

class PayTodayCModel {
    private val repo = AppRepositoryImpl.getInstance()
    fun getUsersWithProduct() : Map<UserData, List<ProductData>> {
        return repo.getUsersWithProduct()
    }
    fun getProductById(id : Long) : ProductData {
        return repo.getProductById(id)
    }
    fun updateProduct(productData: ProductData) {
        repo.updateProduct(productData)
    }
    fun getStringPref(key : String, default : String) : String {
        return repo.getStringPref(key, default)
    }
    fun putStringPref(key : String, value : String) {
        repo.putStringPref(key, value)
    }
}