package com.example.room_tutorial.data.localdata.reponsiveViewModel

import com.example.room_tutorial.data.localdata.Model.User.DAOUser.DAOUser
import com.example.room_tutorial.data.localdata.Model.User.DAOUser.User

class UserReponsive (private val dao : DAOUser){
    val getAllusers = dao.getAlluser()
    suspend fun insert (user: User){
        dao.insertuser(user)
    }
    suspend fun update(user: User){
        dao.update(user)
    }
    suspend fun delete(user: User){
        dao.delete(user)
    }
    suspend fun deleteAll(){
        dao.deleteAll()
    }
}