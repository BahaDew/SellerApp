package com.example.sellerapp.presentation.screens.add_product

import com.example.sellerapp.data.model.ProductData

class AddProductViewModel {
    private val model = AddProductModel()
    fun onClickSaveButton(productData: ProductData) {
        model.addProduct(productData)
    }

}