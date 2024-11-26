package com.gianby.mobileapps

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class RegisterActivity : AppCompatActivity(){


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_register)

        val loginLabel = findViewById<View>(R.id.loginLabel)
        loginLabel.setOnClickListener{
            val goToLoginIntent = Intent(this,LoginActivity::class.java)
            goToLoginIntent.flags  = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(goToLoginIntent)
            finish()
        }
        val registerButton = findViewById<Button>(R.id.loginButton)
        registerButton.setOnClickListener {
            handleRegistration()
        }

    }

    fun handleRegistration(){
        val emailInputLayout = findViewById<TextInputLayout>(R.id.emailInputLayout)
        val passwordInputLayout = findViewById<TextInputLayout>(R.id.passwordInputLayout)
        val fullnameInputLayout = findViewById<TextInputLayout>(R.id.fullnameInputLayout)
        val emailEditText = findViewById<TextInputEditText>(R.id.emailInput)
        val fullnameEditText = findViewById<TextInputEditText>(R.id.fullnameInput)
        val passwordEditText = findViewById<TextInputEditText>(R.id.passwordInput)
        val phoneEditText = findViewById<TextInputEditText>(R.id.phoneInput)
        val checkbox = findViewById<CheckBox>(R.id.checkbox)
        val fullname = fullnameEditText.text.toString()
        val email = emailEditText.text.toString()
        val password = passwordEditText.text.toString()
        val phone = phoneEditText.text.toString()

        if(!checkbox.isChecked){
            checkbox.error = "You need to accept the terms of service"
            return
        }

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

        if(CredentialsManager.register(fullname,email,phone,password)){
            Toast.makeText(this, "User registered correctly, please login!", Toast.LENGTH_SHORT).show()
            val goToLoginIntent = Intent(this,LoginActivity::class.java)
            goToLoginIntent.flags  = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(goToLoginIntent)
        }else{
            emailInputLayout.error = "Email already in use"
            return
        }
    }
}