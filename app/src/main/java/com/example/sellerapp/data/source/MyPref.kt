package com.example.sellerapp.data.source

import android.content.Context
import android.content.SharedPreferences

class MyPref private constructor() {
    companion object {
        private lateinit var instance : MyPref
        private lateinit var shrp : SharedPreferences
        fun init(context: Context) {
            if(!(::instance.isInitialized)) {
                instance = MyPref()
                shrp = context.getSharedPreferences("SellerApp", Context.MODE_PRIVATE)
            }
        }
        fun getInstance() : MyPref {
            return instance
        }
    }
    fun putString(key : String, value : String) {
        shrp.edit().putString(key, value).apply()
    }
    fun getString(key : String, default : String) : String {
        return shrp.getString(key, default)!!
    }
    fun putBoolean(key : String, value : Boolean) {
        shrp.edit().putBoolean(key, value).apply()
    }
    fun getBoolean(key : String, default : Boolean) : Boolean {
        return shrp.getBoolean(key, default)
    }
    fun putLong(key : String, value : Long) {
        shrp.edit().putLong(key, value).apply()
    }
    fun getLong(key : String, default : Long) : Long {
        return shrp.getLong(key, default)
    }
}