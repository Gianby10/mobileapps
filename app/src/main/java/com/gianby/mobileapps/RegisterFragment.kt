package com.gianby.mobileapps

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class RegisterFragment : Fragment(R.layout.fragment_register) {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val registerButton = view.findViewById<Button>(R.id.loginButton)
        registerButton.setOnClickListener {
            handleRegistration(view)
        }

        val loginLabel = view.findViewById<View>(R.id.loginLabel)
        loginLabel.setOnClickListener{

            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container_view, LoginFragment())
                .addToBackStack(null)
                .commit()
        }
    }

    fun handleRegistration(view: View){
        val emailInputLayout = view.findViewById<TextInputLayout>(R.id.emailInputLayout)
        val passwordInputLayout = view.findViewById<TextInputLayout>(R.id.passwordInputLayout)
        val fullnameInputLayout = view.findViewById<TextInputLayout>(R.id.fullnameInputLayout)
        val emailEditText = view.findViewById<TextInputEditText>(R.id.emailInput)
        val fullnameEditText = view.findViewById<TextInputEditText>(R.id.fullnameInput)
        val passwordEditText = view.findViewById<TextInputEditText>(R.id.passwordInput)
        val phoneEditText = view.findViewById<TextInputEditText>(R.id.phoneInput)
        val checkbox = view.findViewById<CheckBox>(R.id.checkbox)
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
            //Toast.makeText(this, "User registered correctly, please login!", Toast.LENGTH_SHORT).show()
            parentFragmentManager.popBackStack()
        }else{
            emailInputLayout.error = "Email already in use"
            return
        }
    }
}