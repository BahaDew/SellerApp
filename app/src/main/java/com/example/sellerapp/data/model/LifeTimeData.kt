package com.example.sellerapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class LifeTimeData(
    @PrimaryKey(autoGenerate = true) val id : Long,
    val date : String,
    val summa : String,
    val payment : String,
    val userId : Long,
)