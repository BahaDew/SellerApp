package com.example.sellerapp.presentation.screens.userInfo

import androidx.lifecycle.MutableLiveData
import com.example.sellerapp.data.model.ProductData
import com.example.sellerapp.data.model.UserData

class ClientInfoViewModel {
    private val model = ClientInfoModel()
    val userLD = MutableLiveData<UserData>()
    val productLD = MutableLiveData<List<ProductData>>()
    val userCallLD = MutableLiveData<UserData>()
    fun getUserById(id: Long) {
        userLD.value = model.getUserById(id)
    }
    fun getProductById(id : Long) {
        productLD.value = model.getProduct(id)
    }
    fun onClickCall(userId : Long) {
        userCallLD.value = model.getUserById(userId)
    }
}