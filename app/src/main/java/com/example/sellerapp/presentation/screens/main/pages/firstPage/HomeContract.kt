package com.example.sellerapp.presentation.screens.main.pages.firstPage

import com.example.sellerapp.data.model.UserData

interface HomeContract {
    interface Model{
        fun addClient(userData: UserData)
    }
    interface View{

    }
    interface ViewModel{
        fun addClient(userData: UserData)
    }

}
