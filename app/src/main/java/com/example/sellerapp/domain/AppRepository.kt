package com.example.sellerapp.domain

import com.example.sellerapp.data.model.ProductData
import com.example.sellerapp.data.model.UserData

interface AppRepository {
    fun getAllProduct(): List<ProductData>
    fun addProduct(data: ProductData)
    fun updateProduct(data: ProductData)
    fun deleteProduct(data: ProductData)
    fun deleteProductByUserId(userId: Long)
    fun getProductById(id: Long): ProductData

    fun getAllUser(): List<UserData>
    fun addUser(data: UserData): Long
    fun updateUser(data: UserData)
    fun deleteUser(data: UserData)
    fun getUserById(id: Long): UserData
    fun getPayTodayUsers(long: Long): List<UserData>
    fun getPayLateUsers(long: Long): List<UserData>
    fun getProductByUserId(userId: Long): List<ProductData>
    fun getUsersWithProduct() : Map<UserData, List<ProductData>>

    fun putStringPref(key : String, value : String)
    fun getStringPref(key : String, default : String) : String
    fun putBooleanPref(key : String, value : Boolean)
    fun getBooleanPref(key : String, default : Boolean) : Boolean
    fun putLongPref(key : String, value : Long)
    fun getLongPref(key : String, default : Long) : Long
}