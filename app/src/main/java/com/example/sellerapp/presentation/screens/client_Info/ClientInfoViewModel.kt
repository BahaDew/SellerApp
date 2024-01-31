package com.example.sellerapp.presentation.screens.client_Info

import androidx.lifecycle.MutableLiveData
import com.example.sellerapp.data.model.ProductData
import com.example.sellerapp.data.model.UserData

class ClientInfoViewModel {
    private val model = ClientInfoModel()
    val userLD = MutableLiveData<UserData>()
    val productsLD = MutableLiveData<List<ProductData>>()
    val userCallLD = MutableLiveData<UserData>()
    fun getUserById(id: Long) {
        userLD.value = model.getUserById(id)
    }
    fun getProductByUserId(userId : Long) {
        productsLD.value = model.getProductsByUser(userId)
    }
    fun onClickCall(userId : Long) {
        userCallLD.value = model.getUserById(userId)
    }
    fun deleteProduct(productData: ProductData) {
        model.deleteProduct(productData)
    }
}