package com.gianby.mobileapps

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class RegisterActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_register)

        val registerNowLabel = findViewById<View>(R.id.loginLabel)
        registerNowLabel.setOnClickListener{
            val goToLoginIntent = Intent(this,LoginActivity::class.java)
            goToLoginIntent.flags  = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(goToLoginIntent)
            finish()
        }


    }
}