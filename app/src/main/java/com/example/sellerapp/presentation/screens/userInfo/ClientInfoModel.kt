package com.example.sellerapp.presentation.screens.userInfo

import com.example.sellerapp.data.model.UserData
import com.example.sellerapp.domain.AppRepository
import com.example.sellerapp.domain.AppRepositoryImpl

class ClientInfoModel {
    private val repo :AppRepository  = AppRepositoryImpl()
    fun getUserById(id: Long) : UserData {
        return repo.getUserById(id)
    }
}