package com.example.room_tutorial.data.localdata.Model.User.DAOUser

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class User (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "user_id")
    val id : Int,
    @ColumnInfo(name = "user_name")
    var email : String,
    @ColumnInfo (name = "user_email")
    var password : String
)