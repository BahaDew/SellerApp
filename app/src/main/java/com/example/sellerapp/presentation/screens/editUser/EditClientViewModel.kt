package com.example.sellerapp.presentation.screens.editUser

import androidx.lifecycle.MutableLiveData
import com.example.sellerapp.data.model.UserData
import com.example.sellerapp.presentation.screens.editUser.EditClientContract
import com.example.sellerapp.presentation.screens.editUser.EditClientModel

class EditClientViewModel : EditClientContract.ViewModel {
    val model = EditClientModel()
    var transferData = MutableLiveData<List<UserData>>()
    fun loadData() {
        transferData.value = model.getAllUsers()
    }

    override fun addClient(userData: UserData) {
        model.addClient(userData)
        loadData()
    }

    override fun editClient(userData: UserData) {
        model.editClient(userData)
        loadData()
    }
}