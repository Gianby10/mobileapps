package com.gianby.mobileapps

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class LoginFragment : Fragment(R.layout.fragment_login) {
    private val credentialsManager: CredentialsManager
        get() = MobileApplication.getInstance().credentialsManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val loginButton = view.findViewById<Button>(R.id.loginButton)
        loginButton.setOnClickListener {
            handleLogin(view)
        }

        val registerNowLabel = view.findViewById<TextView>(R.id.registerNowLabel)
        registerNowLabel.setOnClickListener {

            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container_view, RegisterFragment())
                .addToBackStack(null)
                .commit()
        }
    }

    private fun handleLogin(view:View) {
        val emailInputLayout = view.findViewById<TextInputLayout>(R.id.emailInputLayout)
        val passwordInputLayout =  view.findViewById<TextInputLayout>(R.id.passwordInputLayout)
        val emailEditText =  view.findViewById<TextInputEditText>(R.id.emailInput)
        val passwordEditText =  view.findViewById<TextInputEditText>(R.id.passwordInput)
        val email = emailEditText.text.toString()
        val password = passwordEditText.text.toString()

        // Validate email
        if (!credentialsManager.isEmailValid(email)) {
            emailInputLayout.error = "Invalid email format"
            return
        } else {
            emailInputLayout.error = null
        }

        // Validate password
        if (!credentialsManager.isPasswordValid(password)) {
            passwordInputLayout.error = "Password cannot be empty"
            return
        } else {
            passwordInputLayout.error = null
        }


        if (credentialsManager.login(email, password)) {
            // go to MainActivity
            val intent = Intent(requireContext(), MainActivity::class.java)
            startActivity(intent)
            activity?.finish()
        } else {
            emailInputLayout.error = "Invalid credentials"
            passwordInputLayout.error = "Invalid credentials"
        }
    }


}