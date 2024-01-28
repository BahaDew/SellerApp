package com.example.sellerapp.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("product_table")
data class ProductData(
    @PrimaryKey(autoGenerate = true) val id: Long,
    @ColumnInfo("product_name") val productName : String,
    @ColumnInfo("price_product")val priceProduct: Int,
    @ColumnInfo("userId")val userId: Long,
    @ColumnInfo("month_of_rent") val monthOfRent: String,
    @ColumnInfo("start_date") val startDate : Long,
    @ColumnInfo("comment") val comment: String,
    @ColumnInfo("check_pay") val checkPay: Long,
    @ColumnInfo("advance_payment") val advance_payment: String,
)