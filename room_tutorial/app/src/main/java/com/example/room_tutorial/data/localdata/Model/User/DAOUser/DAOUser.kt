package com.example.room_tutorial.data.localdata.Model.User.DAOUser

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface DAOUser {
    @Insert
    suspend fun insertuser(user : User): Long
    @Update
    suspend fun update (user: User)
    @Delete
    suspend fun delete (user: User)
    @Query("Delete from user")
    suspend fun deleteAll()

    @Query("Select * from user")
    fun getAlluser() : LiveData<List<User>>
    /*@Insert
    fun insertuser2(user: User) : Long
    @Insert
    fun insertusers(user: User, user2: User, user3: User) : List<Long>
    @Insert
    fun insertusers1(users: List<User>) : List<Long>
    @Insert
    fun insertusers2(user: User,users: List<User>) : List<Long>*/
}