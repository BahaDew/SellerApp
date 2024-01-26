package com.example.sellerapp.presentation.screens.editUser

import com.example.sellerapp.data.model.UserData
import com.example.sellerapp.domain.AppRepositoryImpl

class EditClientModel : EditClientContract.Model {
    private val repository = AppRepositoryImpl.getInstance()
    override fun getAllUsers(): List<UserData> {
        return repository.getAllUser()
    }

    override fun editClient(userData: UserData) {
        repository.updateUser(userData)
    }

    override fun addClient(userData: UserData) {
        repository.addUser(userData)
    }
}