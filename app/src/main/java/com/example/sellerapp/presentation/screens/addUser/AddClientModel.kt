package com.example.sellerapp.presentation.screens.addUser

import com.example.sellerapp.data.model.UserData
import com.example.sellerapp.domain.AppRepositoryImpl
import com.example.sellerapp.presentation.screens.editUser.EditClientContract

class AddClientModel : EditClientContract.Model {
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