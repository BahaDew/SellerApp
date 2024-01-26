package com.example.sellerapp.presentation.screens.addUser

import com.example.sellerapp.data.model.UserData
import com.example.sellerapp.domain.AppRepositoryImpl

class AddClientModel : AddClientContract.Model {
    private val repository = AppRepositoryImpl.getInstance()
    override fun getAllUsers(): List<UserData> {
        return repository.getAllUser()
    }

    override fun addClient(userData: UserData) {
        repository.addUser(userData)
    }
}