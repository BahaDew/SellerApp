package com.example.sellerapp.domain

import com.example.sellerapp.data.model.ProductData
import com.example.sellerapp.data.model.UserData
import com.example.sellerapp.data.source.MyDatabase
import com.example.sellerapp.data.source.MyPref

class AppRepositoryImpl : AppRepository {
    companion object {
        private lateinit var instance: AppRepository
        fun getInstance(): AppRepository {
            if (!(::instance.isInitialized)) instance = AppRepositoryImpl()
            return instance
        }
    }
    private val myPref = MyPref.getInstance()
    private val db = MyDatabase.getInstance()

    override fun getAllProduct(): List<ProductData> = db.getProductDao().getAllProduct()

    override fun addProduct(data: ProductData) = db.getProductDao().insertProduct(data)

    override fun updateProduct(data: ProductData) = db.getProductDao().updateProduct(data)

    override fun deleteProduct(data: ProductData) = db.getProductDao().deleteProduct(data)
    override fun deleteProductByUserId(userId: Long) {
        db.getProductDao().deleteProductByUserId(userId)
    }

    override fun getProductById(id: Long): ProductData {
        return db.getProductDao().getProductById(id)
    }

    override fun getAllUser(): List<UserData> = db.getUserDao().getAllUsers()

    override fun addUser(data: UserData): Long {

        return db.getUserDao().insertUser(data)
    }

    override fun updateUser(data: UserData) = db.getUserDao().updateUser(data)
    override fun getUserById(id: Long) = db.getUserDao().getUserById(id)
    override fun getPayTodayUsers(long: Long): List<UserData> {
        return listOf()
    }

    override fun getPayLateUsers(long: Long): List<UserData> {
        return listOf()
    }

    override fun getProductByUserId(userId: Long): List<ProductData> {
        return db.getProductDao().getProductByUserId(userId)
    }

    override fun getUsersWithProduct(): Map<UserData, List<ProductData>> {
        return db.getUserDao().getUsersWithProduct()
    }

    override fun putStringPref(key: String, value: String) {
        myPref.putString(key, value)
    }

    override fun getStringPref(key: String, default: String) : String {
        return myPref.getString(key, default)
    }

    override fun putBooleanPref(key: String, value: Boolean) {
        myPref.putBoolean(key, value)
    }

    override fun getBooleanPref(key: String, default: Boolean) : Boolean {
        return myPref.getBoolean(key, default)
    }

    override fun putLongPref(key: String, value: Long) {
        myPref.putLong(key, value)
    }

    override fun getLongPref(key: String, default: Long): Long {
        return myPref.getLong(key, default)
    }

    override fun deleteUser(data: UserData) = db.getUserDao().deleteUser(data)
}