package com.example.sellerapp.presentation.screens.addUser

import com.example.sellerapp.data.model.UserData

interface AddClientContract {


    interface View{

    }
    interface Model{
        fun addClient(userData: UserData)
    }
    interface ViewModel{
        fun addClient(userData: UserData)
    }

}
