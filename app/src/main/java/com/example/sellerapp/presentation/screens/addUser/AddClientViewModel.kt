package com.example.sellerapp.presentation.screens.addUser

import androidx.lifecycle.MutableLiveData
import com.example.sellerapp.data.model.UserData

class AddClientViewModel : AddClientContract.ViewModel {
    val model = AddClientModel()
    var transferData = MutableLiveData<List<UserData>>()
    fun loadData() {
        transferData.value = model.getAllUsers()
    }

    override fun addClient(userData: UserData) {
        model.addClient(userData)
        loadData()
    }
}