package com.example.sellerapp.data.model

import androidx.room.Entity

@Entity
data class LifeTimeData(
    val id : Long,
    val date : String,
    val summa : String,
    val payment : String,
    val userId : Long,
)