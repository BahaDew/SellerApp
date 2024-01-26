package com.example.sellerapp.presentation.screens.main.pages.firstPage

import com.example.sellerapp.data.model.UserData
import com.example.sellerapp.presentation.screens.addUser.AddClientModel

class HomeViewModel : HomeContract.Model {
    val model = HomeModel()
    override fun addClient(userData: UserData) {
        model.addClient(userData)
    }
}