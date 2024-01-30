package com.example.sellerapp.data.model

data class TodayPaysData(
    val productId : Long,
    val userId : Long,
    val fullName : String,
    val phoneNumber : String,
    val productName : String,
    val priceProduct : Double,
    val startDate : Long,
    val payingDeadline : Long,
    var comment : String
)