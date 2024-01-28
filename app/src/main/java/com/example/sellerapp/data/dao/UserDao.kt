package com.example.sellerapp.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.sellerapp.data.model.UserData

@Dao
interface UserDao {

    @Query("SELECT * FROM user_table")
    fun getAllUsers() : List<UserData>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(data: UserData) : Long

    @Update
    fun updateUser(data: UserData)

    @Delete
    fun deleteUser(data: UserData)

    @Query("SELECT * FROM user_table WHERE id = :id")
    fun getUserById(id: Long): UserData

//    @Query("SELECT * FROM user_table WHERE product_id = :id LIMIT 1")
//    fun getProductById(id : Long) : UserData

}