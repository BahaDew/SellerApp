package com.example.sellerapp.presentation.screens.addUser

import androidx.lifecycle.MutableLiveData
import com.example.sellerapp.data.model.ProductData
import com.example.sellerapp.data.model.UserData

class AddClientViewModel{
    val model = AddClientModel()
    var transferData = MutableLiveData<List<UserData>>()
    fun loadData() {
        transferData.value = model.getAllUsers()
    }

    fun addClientAndProduct(userData: UserData, productData: ProductData) {
        val userId = model.addClient(userData)
        model.addProduct(productData.copy(userId = userId))
        loadData()
    }
}