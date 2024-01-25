package com.example.sellerapp.data.source

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.sellerapp.data.dao.ProductDao
import com.example.sellerapp.data.dao.UserDao
import com.example.sellerapp.data.model.ProductData
import com.example.sellerapp.data.model.UserData

@Database(entities = [ProductData::class,UserData::class], version = 1)
abstract class MyDatabase : RoomDatabase(){
    abstract fun getProductDao() : ProductDao
    abstract fun getUserDao() : UserDao

    companion object{
        private lateinit var instance : MyDatabase
        fun init(context: Context){
            if (!(::instance.isInitialized)){
                instance = Room.databaseBuilder(context,MyDatabase::class.java,"Seller.db")
                    .allowMainThreadQueries()
                    .build()
            }
        }
        fun getInstance() : MyDatabase = instance
    }
}