package com.example.sellerapp.presentation.screens.userInfo

import com.example.sellerapp.data.model.UserData

class ClientInfoViewModel {
    val model = ClientInfoModel()
    fun getUserById(id:Long) : UserData {
      return  model.getUserById(id)
    }
}