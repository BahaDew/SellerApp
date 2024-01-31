package com.example.sellerapp.presentation.screens.edit_product

import com.example.sellerapp.data.model.ProductData
import com.example.sellerapp.domain.AppRepository
import com.example.sellerapp.domain.AppRepositoryImpl

class EditProductModel {
    private val repo : AppRepository = AppRepositoryImpl.getInstance()
    fun addProduct(productData: ProductData) {
        repo.addProduct(productData)
    }
    fun getProductById(productId : Long) : ProductData {
        return repo.getProductById(productId)
    }
    fun updateProduct(productData: ProductData) {
        repo.updateProduct(productData)
    }
}