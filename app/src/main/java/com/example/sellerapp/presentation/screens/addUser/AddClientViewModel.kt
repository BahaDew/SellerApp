package com.example.sellerapp.presentation.screens.addUser

import com.example.sellerapp.data.model.UserData

class AddClientViewModel : AddClientContract.ViewModel {
    val model = AddClientModel()

    override fun addClient(userData: UserData) {
        model.addClient(userData)
    }
}