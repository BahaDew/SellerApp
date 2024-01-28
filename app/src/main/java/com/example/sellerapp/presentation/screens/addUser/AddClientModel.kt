package com.example.sellerapp.presentation.screens.addUser

import com.example.sellerapp.data.model.ProductData
import com.example.sellerapp.data.model.UserData
import com.example.sellerapp.domain.AppRepositoryImpl

class AddClientModel {
    private val repository = AppRepositoryImpl.getInstance()
    fun getAllUsers(): List<UserData> {
        return repository.getAllUser()
    }

    fun addClient(userData: UserData): Long {
        return repository.addUser(userData)
    }

    fun addProduct(productData: ProductData) {
        repository.addProduct(productData)
    }
}