package com.example.sellerapp.presentation.screens.main.pages.firstPage

import com.example.sellerapp.data.model.UserData
import com.example.sellerapp.domain.AppRepositoryImpl

class HomeModel : HomeContract.Model{
    private val repository = AppRepositoryImpl.getInstance()
    override fun addClient(userData: UserData) {
        repository.addUser(userData)
    }

    override fun getAllClients(): List<UserData> {
        return repository.getAllUser()
    }

    override fun deleteUser(userData: UserData) {
        repository.deleteUser(userData)
    }

}
