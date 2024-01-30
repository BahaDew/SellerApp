package com.example.sellerapp.presentation.screens.main.pages.secondPage

import androidx.lifecycle.MutableLiveData
import com.example.sellerapp.data.model.ProductData
import com.example.sellerapp.data.model.UserData

class PayTodayCViewModel {
    val usersWithProductLD = MutableLiveData<Map<UserData, List<ProductData>>>()
    val oldSmsLD = MutableLiveData<String>()
    private val model = PayTodayCModel()
    fun getAllUsersWithProduct() {
        usersWithProductLD.value = model.getUsersWithProduct()
    }
    fun onClickSave(id : Long, comment : String) {
        model.updateProduct(model.getProductById(id).copy(comment = comment))
    }
    fun onClickSms() {
        oldSmsLD.value = model.getStringPref("OLD_SMS", "")
    }
    fun onClickSend(message : String) {
        model.putStringPref("OLD_SMS", message)
    }
}