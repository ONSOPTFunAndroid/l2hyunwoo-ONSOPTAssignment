package com.example.onsoptfirstassignment.preference

import android.app.Application

class LoginApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        LoginPreference.init(this)
    }
}