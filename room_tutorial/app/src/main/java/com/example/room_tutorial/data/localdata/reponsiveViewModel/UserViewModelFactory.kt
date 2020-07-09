package com.example.room_tutorial.data.localdata.reponsiveViewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class UserViewModelFactory(private val reponsive: UserReponsive, private val context: Context): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserViewModel::class.java)){
            return UserViewModel(reponsive, context) as T
        }
        throw IllegalArgumentException("Unknow View Model class")

    }

}