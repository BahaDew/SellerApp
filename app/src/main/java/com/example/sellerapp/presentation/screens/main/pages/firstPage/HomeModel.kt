package com.example.sellerapp.presentation.screens.main.pages.firstPage

import com.example.sellerapp.data.model.UserData
import com.example.sellerapp.domain.AppRepositoryImpl

class HomeModel {
    private val repository = AppRepositoryImpl.getInstance()
    fun addClient(userData: UserData) {
        repository.addUser(userData)
    }

    fun getAllClients(): List<UserData> {
        return repository.getAllUser()
    }

    fun deleteUser(userData: UserData) {
        repository.deleteUser(userData)
    }

}
