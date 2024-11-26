package com.gianby.mobileapps

import org.junit.Test
import org.junit.jupiter.api.Assertions.*

class CredentialsManagerTest {
    @Test
    fun givenEmptyEmail_thenReturnFalse(){

        val email = ""
        val result = CredentialsManager.isEmailValid(email)

        assertFalse(result)
    }

    @Test
    fun givenWrongEmailFormat_thenReturnFalse(){

        val email = "not an email!"
        val result = CredentialsManager.isEmailValid(email)

        assertFalse(result)
    }

    @Test
    fun givenProperEmailFormat_thenReturnTrue(){

        val email = "test@test.com"
        val result = CredentialsManager.isEmailValid(email)

        assertTrue(result)
    }

    @Test
    fun givenEmptyPassword_thenReturnFalse(){

        val password = ""
        val result = CredentialsManager.isPasswordValid(password)

        assertFalse(result)
    }

    @Test
    fun givenProperPassword_thenReturnTrue(){

        val password = "test"
        val result = CredentialsManager.isPasswordValid(password)

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

    @Test
    fun givenCorrectCredentials_whenUserRegisters_thenCreateNewCredentials(){



        CredentialsManager.register(
            "Full name","email@te.st","6060606","1234ber"
        )

        val isLoginSuccessful = CredentialsManager.login("email@te.st","1234")
        assertTrue(isLoginSuccessful)
    }

    @Test
    fun givenDuplicateEmail_whenUserRegisters_thenReturnFalse() {
        CredentialsManager.register("Full Name", "duplicate@te.st", "6060606", "password1")
        val result = CredentialsManager.register("Another Name", "duplicate@te.st", "7070707", "password2")

        assertFalse(result)
    }

    @Test
    fun givenIncorrectPassword_whenLoggingIn_thenReturnFalse() {
        CredentialsManager.register("Full Name", "email@te.st", "6060606", "password")
        val result = CredentialsManager.login("email@te.st", "wrongpassword")

        assertFalse(result)
    }

    @Test
    fun givenNonExistentUser_whenLoggingIn_thenReturnFalse() {
        val result = CredentialsManager.login("nonexistent@te.st", "password")
        assertFalse(result)
    }


}