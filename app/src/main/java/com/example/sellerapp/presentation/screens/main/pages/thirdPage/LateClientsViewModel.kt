package com.example.sellerapp.presentation.screens.main.pages.thirdPage

import androidx.lifecycle.MutableLiveData
import com.example.sellerapp.data.model.ProductData
import com.example.sellerapp.data.model.UserData

class LateClientsViewModel {
    val usersWithProductLD = MutableLiveData<Map<UserData, List<ProductData>>>()
    private val model = LateClientsModel()
    fun getAllUsersWithProduct() {
        usersWithProductLD.value = model.getUsersWithProduct()
    }
    fun onClickSave(id : Long, comment : String) {
        model.updateProduct(model.getProductById(id).copy(comment = comment))
    }
}