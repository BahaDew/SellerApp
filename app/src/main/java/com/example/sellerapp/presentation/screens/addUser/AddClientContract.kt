package com.example.sellerapp.presentation.screens.addUser

import com.example.sellerapp.data.model.UserData

interface AddClientContract {


    interface View{

    }
    interface Model{
        fun addClient(userData: UserData)
        fun getAllUsers(): List<UserData>?
    }
    interface ViewModel{
        fun addClient(userData: UserData)
    }

}
