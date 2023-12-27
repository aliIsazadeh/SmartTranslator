package com.esum.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "profile_table")
data class ProfileEntity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("id" )
    val id: Long = 0L ,

    @ColumnInfo("name")
    val name : String,

    @ColumnInfo("password")
    val password : String ? = null,

    @ColumnInfo("username")
    val username : String,

    @ColumnInfo("email")
    val email : String,

    @ColumnInfo("phone_number")
    val phoneNumber : String,

    @ColumnInfo("avatar")
    val avatar : String?= null,



)