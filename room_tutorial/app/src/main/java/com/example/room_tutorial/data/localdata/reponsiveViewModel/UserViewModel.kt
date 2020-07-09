package com.example.room_tutorial.data.localdata.reponsiveViewModel

import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.room_tutorial.View.LoginSuccess
import com.example.room_tutorial.View.checkmail
import com.example.room_tutorial.data.LiveData.Event
import com.example.room_tutorial.data.localdata.Model.User.DAOUser.User
import kotlinx.coroutines.launch
import java.util.regex.Matcher
import java.util.regex.Pattern

class UserViewModel (private val reponsive: UserReponsive, private val context: Context) : ViewModel(), Observable {
    val getAllusers = reponsive.getAllusers
    @Bindable
    val messagelogin = MutableLiveData<String>()
    @Bindable
    val inputPassword = MutableLiveData<String>()
    @Bindable
    val inputEmail = MutableLiveData<String>()
    @Bindable
    val saveorupdateButton = MutableLiveData<String>()
    private val statusmessage = MutableLiveData<Event<String>>()
    val message : LiveData<Event<String>>
        get() = statusmessage

    init {
        saveorupdateButton.value = "Login"
    }
    fun save(){
        var check = 0;
        if (inputEmail.value == null || inputPassword.value == null){
            statusmessage.value = Event(" pls ")
        }else{
            val password = inputPassword.value!!
            val email = inputEmail.value!!

            val specialCharacter = "`~!@#$%^&*()_=-?><,.':;{}|"
            val regex = Pattern.compile("[`~!@#$%^&*()_=-?><,.':;{}|]")
            val regex2 = Pattern.compile("[0-9]")
            val matcher =regex.matcher(password)
            val matcher2 = regex2.matcher(password)
            val checkresult = checkmail1(email)
            Log.w("result12 : " ," " + checkresult)
            if (checkresult){
                if (matcher.find() && matcher2.find() && password.length >=6 ){
                    for (i in 0 until password.length )
                        if (specialCharacter.contains(Character.toString(password[i]))){
                            check++;
                        }
                    if (check == 1){
                        insert(User(0, email, password))
                        val intent = Intent(context, LoginSuccess::class.java)
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(context,intent, null)
                        statusmessage.value = Event(" Login Successfully")
                    }else{
                        statusmessage.value = Event("pls check email password")
                        messagelogin.value = "pls check mail and password "
                    }
                }else{
                    statusmessage.value = Event("password include 1 characterspecial and number character!")
                    messagelogin.value = "password include 1 characterspecial and number character1!"
                }
            }else{
                messagelogin.value = "pls check validate mail "
            }

            inputPassword.value = null
            inputEmail.value = null
            messagelogin.value = null
        }



    }
    fun insert(user: User) = viewModelScope.launch{
        reponsive.insert(user)
        statusmessage.value = Event("User insert successfully")
    }

    fun checkmail1(textmail : String): Boolean {
        val check = checkmail()
        val result = check.validate(textmail)
        Log.w("result : " ," " + result)
        return result
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
    }
}