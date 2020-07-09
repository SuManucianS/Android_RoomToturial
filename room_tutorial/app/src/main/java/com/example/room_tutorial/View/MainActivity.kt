package com.example.room_tutorial.View

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.room_tutorial.R
import com.example.room_tutorial.data.localdata.Model.User.DAOUser.User
import com.example.room_tutorial.data.localdata.Model.User.UserDatabase
import com.example.room_tutorial.data.localdata.reponsiveViewModel.UserReponsive
import com.example.room_tutorial.data.localdata.reponsiveViewModel.UserViewModelFactory
import com.example.room_tutorial.data.localdata.reponsiveViewModel.UserViewModel
import com.example.room_tutorial.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: UserViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this,
            R.layout.activity_main
        )
        val dao = UserDatabase.getInstance(application).userDAO
        val reponsive = UserReponsive(dao)
        val factory = UserViewModelFactory(reponsive, applicationContext)
        viewModel = ViewModelProvider(this, factory).get(UserViewModel::class.java)
        binding.userViewModel =viewModel
        binding.lifecycleOwner = this
        //initRecyleview()
        viewModel.message.observe(this,  Observer {
            it.getContentIfNotHandled()?.let {
                Toast.makeText(this, it, Toast.LENGTH_LONG).show()
            }
        })
    }
}
