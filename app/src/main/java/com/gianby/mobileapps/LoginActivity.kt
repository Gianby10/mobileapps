package com.gianby.mobileapps

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login)

        val registerNowLabel = findViewById<TextView>(R.id.registerNowLabel)
        registerNowLabel.setOnClickListener {

            val goToRegisterIntent = Intent(this, RegisterActivity::class.java)
            goToRegisterIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(goToRegisterIntent)
            finish()
        }
    }
}