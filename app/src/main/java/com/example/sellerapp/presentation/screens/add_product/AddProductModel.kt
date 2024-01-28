package com.example.sellerapp.presentation.screens.add_product

import com.example.sellerapp.data.model.ProductData
import com.example.sellerapp.domain.AppRepository
import com.example.sellerapp.domain.AppRepositoryImpl

class AddProductModel {
    private val repo : AppRepository = AppRepositoryImpl.getInstance()
    fun addProduct(productData: ProductData) {
        repo.addProduct(productData)
    }
}