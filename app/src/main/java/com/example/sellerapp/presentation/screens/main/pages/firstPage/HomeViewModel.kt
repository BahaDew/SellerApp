package com.example.sellerapp.presentation.screens.main.pages.firstPage

import androidx.lifecycle.MutableLiveData
import com.example.sellerapp.data.model.UserData
import com.example.sellerapp.presentation.screens.addUser.AddClientModel

class HomeViewModel : HomeContract.ViewModel {
    val model = HomeModel()
    val transferData = MutableLiveData<List<UserData>>()


    override fun addClient(userData: UserData) {
        model.addClient(userData)
        getAllClients()
    }

    override fun getAllClients() {
        transferData.value = model.getAllClients()
    }

}