package com.example.sellerapp.presentation.screens.main.pages.firstPage

import androidx.lifecycle.MutableLiveData
import com.example.sellerapp.data.model.UserData

class HomeViewModel : HomeContract.ViewModel {
    val model = HomeModel()
    val transferData = MutableLiveData<List<UserData>>()


    override fun addClient(userData: UserData) {
        model.addClient(userData)
        getAllClients()
    }

    override fun deleteUser(userData: UserData) {
        model.deleteUser(userData)
        getAllClients()
    }

    override fun getAllClients() : List<UserData> {
        transferData.value = model.getAllClients()
        return model.getAllClients()
    }

}