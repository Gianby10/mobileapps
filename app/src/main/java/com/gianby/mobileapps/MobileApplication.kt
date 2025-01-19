package com.gianby.mobileapps

import android.app.Application

class MobileApplication : Application() {
    val credentialsManager = CredentialsManager()
    companion object {
        private lateinit var instance: MobileApplication
        fun getInstance(): MobileApplication = instance
    }
    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}