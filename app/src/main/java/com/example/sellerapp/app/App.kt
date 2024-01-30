package com.example.sellerapp.app

import android.app.Application
import com.example.sellerapp.data.source.MyDatabase
import com.example.sellerapp.data.source.MyPref

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        MyDatabase.init(this)
        MyPref.init(this)
    }
}