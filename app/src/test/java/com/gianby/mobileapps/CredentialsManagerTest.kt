package com.gianby.mobileapps

import org.junit.Test
import org.junit.jupiter.api.Assertions.*

class CredentialsManagerTest {
    @Test
    fun givenEmptyEmail_thenReturnFalse(){
        val credentialsManager = CredentialsManager()
        val email = ""
        val result = credentialsManager.isEmailValid(email)

        assertFalse(result)
    }

    @Test
    fun givenWrongEmailFormat_thenReturnFalse(){
        val credentialsManager = CredentialsManager()
        val email = "not an email!"
        val result = credentialsManager.isEmailValid(email)

        assertFalse(result)
    }

    @Test
    fun givenProperEmailFormat_thenReturnTrue(){
        val credentialsManager = CredentialsManager()
        val email = "test@test.com"
        val result = credentialsManager.isEmailValid(email)

        assertTrue(result)
    }

    @Test
    fun givenEmptyPassword_thenReturnFalse(){
        val credentialsManager = CredentialsManager()
        val password = ""
        val result = credentialsManager.isPasswordValid(password)

        assertFalse(result)
    }

    @Test
    fun givenProperPassword_thenReturnTrue(){
        val credentialsManager = CredentialsManager()
        val password = "test"
        val result = credentialsManager.isPasswordValid(password)

        assertTrue(result)
    }

    @Test
    fun givenProperCredentials_thenReturnTrue() {
        val email = "test@te.st"
        val password = "1234"
        val result = email == "test@te.st" && password == "1234"
        assertTrue(result)
    }

    @Test
    fun givenWrongCredentials_thenReturnFalse() {
        val email = "wrong@te.st"
        val password = "wrong"
        val result = email == "test@te.st" && password == "1234"
        assertFalse(result)
    }


}