package com.example.room_tutorial.data.localdata.Model.User

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.room_tutorial.data.localdata.Model.User.DAOUser.DAOUser
import com.example.room_tutorial.data.localdata.Model.User.DAOUser.User

@Database(entities = [User :: class], version = 1)
abstract class UserDatabase : RoomDatabase() {
    abstract val userDAO : DAOUser
    companion object{
        @Volatile
        private var INSTANCE : UserDatabase? = null
        fun getInstance(context : Context) : UserDatabase {
            synchronized(this){
                var instance = INSTANCE
                if (instance == null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        UserDatabase::class.java,
                        "user_database"
                    ).build()
                }
                return instance
            }
        }

    }
}