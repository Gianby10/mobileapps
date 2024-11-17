package com.gianby.mobileapps


class CredentialsManager {
    private val emailPattern = ("[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
            "\\@" +
            "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
            "(" +
            "\\." +
            "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
            ")+")

    fun isEmailValid(email: String): Boolean {
        return Regex(emailPattern).matches(email)
    }
    fun isPasswordValid(password: String): Boolean {
        return password.isNotEmpty()
    }
    fun login(email: String, password: String): Boolean {
        return email == "test@te.st" && password == "1234"
    }
}