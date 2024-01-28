package com.example.sellerapp.data.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity("user_table")
data class UserData(
    @PrimaryKey(autoGenerate = true) val id: Long,
    @ColumnInfo("first_name") val firstName: String,
    @ColumnInfo("last_name") val secondName: String,
    @ColumnInfo("phone_number") val phoneNumber: String,
    @ColumnInfo("age") val age: Int
) : Parcelable