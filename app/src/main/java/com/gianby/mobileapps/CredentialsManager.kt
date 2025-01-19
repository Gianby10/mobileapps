package com.gianby.mobileapps

import android.util.Log
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow


class CredentialsManager {
    private val _isLoggedIn = MutableStateFlow(false)
    val isLoggedIn: StateFlow<Boolean> = _isLoggedIn.asStateFlow()
    private val emailPattern = ("[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
            "\\@" +
            "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
            "(" +
            "\\." +
            "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
            ")+")

    val credentials = mutableMapOf<String,String>(

        Pair("email@te.st","1234")
    )

    fun isEmailValid(email: String): Boolean {
        return Regex(emailPattern).matches(email)
    }
    fun isPasswordValid(password: String): Boolean {
        return password.isNotEmpty()
    }
    fun login(email: String, password: String): Boolean {
        if(credentials.contains(email)){
            if(credentials[email] == password){
                _isLoggedIn.value = true
                return true
            }
        }
        return false
    }
    fun register(fullName:String,email:String,phone:String,password: String): Boolean{
        if (credentials.containsKey(email)) {
            return false
        }
        credentials.put(email,password)
        credentials.forEach { (key, value) ->
            Log.i("Credentials", "Key: $key, Value: $value")
        }

        return true
    }

    fun logout() {
        _isLoggedIn.value = false
    }
}