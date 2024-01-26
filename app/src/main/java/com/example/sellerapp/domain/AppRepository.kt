package com.example.sellerapp.domain

import com.example.sellerapp.data.model.ProductData
import com.example.sellerapp.data.model.UserData

interface AppRepository {
    fun getAllProduct() : List<ProductData>
    fun addProduct(data: ProductData)
    fun updateProduct(data: ProductData)
    fun deleteProduct(data: ProductData)
    fun getProductById(id: Long): ProductData

    fun getAllUser() : List<UserData>
    fun addUser(data: UserData)
    fun updateUser(data: UserData)
    fun deleteUser(data: UserData)
    fun getUserById(id: Long) : UserData

    fun getProductById(id : Long) : ProductData
//    fun getUserFromProduct(product_id : Long) : UserData
}