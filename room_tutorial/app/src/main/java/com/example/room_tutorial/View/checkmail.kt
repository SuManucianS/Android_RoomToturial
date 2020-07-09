package com.example.room_tutorial.View

import java.util.regex.Matcher
import java.util.regex.Pattern

class checkmail {
    private var pattern: Pattern
    private lateinit var matcher : Matcher

    init{
        pattern = Pattern.compile(EMAIL_PATTERN)
    }
    fun validate(email:String):Boolean {
        matcher = pattern.matcher(email)
        return matcher.matches()
    }
    companion object {
        private val EMAIL_PATTERN = ("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")
    }
}