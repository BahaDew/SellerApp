package com.example.sellerapp.presentation.screens.main.pages.firstPage

import com.example.sellerapp.data.model.UserData

interface HomeContract {
    interface Model{
        fun addClient(userData: UserData)
        fun getAllClients() : List<UserData>
    }
    interface View{

    }
    interface ViewModel{
        fun getAllClients()
        fun addClient(userData: UserData)
    }

}
