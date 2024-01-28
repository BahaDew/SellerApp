package com.example.sellerapp.presentation.screens.main.pages.firstPage

import androidx.lifecycle.MutableLiveData
import com.example.sellerapp.data.model.UserData

class HomeViewModel {
    val model = HomeModel()
    val transferData = MutableLiveData<List<UserData>>()
    fun addClient(userData: UserData) {
        model.addClient(userData)
        getAllClients()
    }

    fun deleteUser(userData: UserData) {
        model.deleteUser(userData)
        getAllClients()
    }

    fun getAllClients(): List<UserData> {
        transferData.value = model.getAllClients()
        return model.getAllClients()
    }
}