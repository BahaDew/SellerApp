package com.example.sellerapp.presentation.screens.userInfo

import android.icu.util.Calendar
import android.icu.util.LocaleData
import android.os.Build
import androidx.annotation.RequiresApi
import com.example.sellerapp.data.model.ProductData
import com.example.sellerapp.data.model.UserData
import com.example.sellerapp.domain.AppRepository
import com.example.sellerapp.domain.AppRepositoryImpl

class ClientInfoModel {
    private val repo: AppRepository = AppRepositoryImpl()
    fun getUserById(id: Long): UserData {
        return repo.getUserById(id)
    }

    fun getProduct(userId: Long): List<ProductData> {
        return repo.getProductByUserId(userId)
    }

}