package com.example.sellerapp.presentation.screens.editUser

import androidx.lifecycle.MutableLiveData
import com.example.sellerapp.data.model.UserData

class EditClientViewModel {
    val model = EditClientModel()
    var transferData = MutableLiveData<List<UserData>>()
    fun loadData() {
        transferData.value = model.getAllUsers()
    }

    fun addClient(userData: UserData) {
        model.addClient(userData)
        loadData()
    }

    fun editClient(userData: UserData) {
        model.editClient(userData)
        loadData()
    }
}