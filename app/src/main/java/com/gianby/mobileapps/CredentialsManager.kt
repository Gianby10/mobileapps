package com.gianby.mobileapps

import android.util.Log


object CredentialsManager {
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
}