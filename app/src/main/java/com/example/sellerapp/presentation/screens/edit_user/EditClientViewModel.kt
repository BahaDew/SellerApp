package com.example.sellerapp.presentation.screens.edit_user

import androidx.lifecycle.MutableLiveData
import com.example.sellerapp.data.model.UserData

class EditClientViewModel {
    val model = EditClientModel()
    var allUserLD = MutableLiveData<List<UserData>>()
    var userLD = MutableLiveData<UserData>()
    fun getAllUser() {
        allUserLD.value = model.getAllUsers()
    }

    fun getUserById(id : Long) {
        userLD.value = model.getUserById(id)
    }

    fun editClient(userData: UserData) {
        model.editClient(userData)
    }
}