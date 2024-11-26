package com.gianby.mobileapps

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login)


        val loginButton = findViewById<Button>(R.id.loginButton)
        loginButton.setOnClickListener {
            handleLogin()
        }




        val registerNowLabel = findViewById<TextView>(R.id.registerNowLabel)
        registerNowLabel.setOnClickListener {

            val goToRegisterIntent = Intent(this, RegisterActivity::class.java)
            goToRegisterIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(goToRegisterIntent)
            finish()
        }
    }

    private fun handleLogin() {
        val emailInputLayout = findViewById<TextInputLayout>(R.id.emailInputLayout)
        val passwordInputLayout = findViewById<TextInputLayout>(R.id.passwordInputLayout)
        val emailEditText = findViewById<TextInputEditText>(R.id.emailInput)
        val passwordEditText = findViewById<TextInputEditText>(R.id.passwordInput)
        val email = emailEditText.text.toString()
        val password = passwordEditText.text.toString()

        // Validate email
        if (!CredentialsManager.isEmailValid(email)) {
            emailInputLayout.error = "Invalid email format"
            return
        } else {
            emailInputLayout.error = null
        }

        // Validate password
        if (!CredentialsManager.isPasswordValid(password)) {
            passwordInputLayout.error = "Password cannot be empty"
            return
        } else {
            passwordInputLayout.error = null
        }


        if (CredentialsManager.login(email, password)) {
            // go to MainActivity
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        } else {
            emailInputLayout.error = "Invalid credentials"
            passwordInputLayout.error = "Invalid credentials"
        }
    }


}