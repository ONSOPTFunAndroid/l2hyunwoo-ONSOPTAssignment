package com.example.onsoptfirstassignment

import android.app.Application
import com.example.onsoptfirstassignment.preference.LoginPreference
import com.example.onsoptfirstassignment.singleton.EncryptedKeyStore

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        LoginPreference.init(this)
        EncryptedKeyStore.init(this)
    }
}