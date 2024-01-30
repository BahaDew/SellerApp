package com.example.sellerapp.presentation.screens.client_Info

import com.example.sellerapp.data.model.ProductData
import com.example.sellerapp.data.model.UserData
import com.example.sellerapp.domain.AppRepository
import com.example.sellerapp.domain.AppRepositoryImpl

class ClientInfoModel {
    private val repo: AppRepository = AppRepositoryImpl()
    fun getUserById(id: Long): UserData {
        return repo.getUserById(id)
    }

    fun getProductsByUser(userId: Long): List<ProductData> {
        return repo.getProductByUserId(userId)
    }

    fun deleteProduct(productData: ProductData) {
        repo.deleteProduct(productData)
    }
}