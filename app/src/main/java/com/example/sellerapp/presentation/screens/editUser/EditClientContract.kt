package com.example.sellerapp.presentation.screens.editUser

import com.example.sellerapp.data.model.UserData

interface EditClientContract {


    interface View{

    }
    interface Model{
        fun addClient(userData: UserData)
        fun getAllUsers(): List<UserData>?
        fun editClient(userData: UserData)
    }
    interface ViewModel{
        fun addClient(userData: UserData)
        fun editClient(userData: UserData)
    }

}
