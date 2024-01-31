package com.example.sellerapp.presentation.screens.edit_user

import com.example.sellerapp.data.model.UserData
import com.example.sellerapp.domain.AppRepositoryImpl

class EditClientModel {
    private val repository = AppRepositoryImpl.getInstance()
    fun getAllUsers(): List<UserData> {
        return repository.getAllUser()
    }
    fun editClient(userData: UserData) {
        repository.updateUser(userData)
    }
    fun getUserById(id : Long) : UserData {
        return repository.getUserById(id)
    }
}