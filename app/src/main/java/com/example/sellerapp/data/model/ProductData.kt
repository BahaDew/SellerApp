package com.example.sellerapp.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("product_table")
data class ProductData(
    @PrimaryKey(autoGenerate = true) val id: Long,
    @ColumnInfo("product_name") val productName : String,
    @ColumnInfo("price_product")val priceProduct: Int
)