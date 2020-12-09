package com.example.onsoptfirstassignment.singleton

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys

object EncryptedKeyStore {
    private val PREF_KEY = "pref"
    private val MEMBER_BASE_URL = "member"
    private val keyGenParameterSpec = MasterKeys.AES256_GCM_SPEC
    private val masterKeyAlias = MasterKeys.getOrCreate(keyGenParameterSpec)
    private lateinit var sharedPref: SharedPreferences

    fun init(context: Context) {
        sharedPref = EncryptedSharedPreferences.create(
            PREF_KEY,
            masterKeyAlias,
            context,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
        sharedPref
            .edit()
            .putString(MEMBER_BASE_URL, "http://15.164.83.210:3000")
            .apply()
    }

    fun getBaseUrl(): String = sharedPref.getString(MEMBER_BASE_URL, "")!!
}