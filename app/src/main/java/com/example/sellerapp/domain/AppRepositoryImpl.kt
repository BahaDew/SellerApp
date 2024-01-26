package com.example.sellerapp.domain

import android.util.Log
import com.example.sellerapp.data.model.ProductData
import com.example.sellerapp.data.model.UserData
import com.example.sellerapp.data.source.MyDatabase

class AppRepositoryImpl : AppRepository {
    companion object{
        private lateinit var instance : AppRepository
        fun getInstance() : AppRepository{
            if (!(::instance.isInitialized)) instance =AppRepositoryImpl()
            return instance
        }
    }
    private val db = MyDatabase.getInstance()

    override fun getAllProduct(): List<ProductData> = db.getProductDao().getAllProduct()

    override fun addProduct(data: ProductData) = db.getProductDao().insertProduct(data)

    override fun updateProduct(data: ProductData) = db.getProductDao().updateProduct(data)

    override fun deleteProduct(data: ProductData) = db.getProductDao().deleteProduct(data)

    override fun getAllUser(): List<UserData> = db.getUserDao().getAllUsers()

    override fun addUser(data: UserData) {
        Log.d("ttt", data.firstName)
        db.getUserDao().insertUser(data)
    }

    override fun updateUser(data: UserData) = db.getUserDao().updateUser(data)
    override fun getUserById(id: Long) = db.getUserDao().getUserById(id)

    override fun deleteUser(data: UserData) = db.getUserDao().deleteUser(data)


//    override fun getUserFromProduct(product_id: Long): UserData = db.getUserDao().getUserFromProduct(product_id)
}