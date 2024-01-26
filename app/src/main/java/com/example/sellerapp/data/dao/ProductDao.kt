package com.example.sellerapp.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.sellerapp.data.model.ProductData

@Dao
interface ProductDao {

    @Query("SELECT * FROM product_table")
    fun getAllProduct() : List<ProductData>


    @Query("SELECT * FROM product_table WHERE id = :id")
    fun getProductById(id:Long) : ProductData

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertProduct(data : ProductData)

    @Update
    fun updateProduct(data: ProductData)


    @Delete
    fun deleteProduct(data: ProductData)
}