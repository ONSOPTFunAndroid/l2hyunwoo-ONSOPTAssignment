package com.example.onsoptfirstassignment.preference

import android.content.Context
import android.content.SharedPreferences

object LoginPreference {

    val PREFES_FILENAME = "prefs"
    val PREF_KEY_ET_MAIL = "mail"
    val PREF_KEY_ET_PASSWORD = "password"
    private lateinit var prefs: SharedPreferences

    private val isLogin = Pair("isLogin", false)
    private val mail = Pair(PREF_KEY_ET_MAIL, "")
    private val password = Pair(PREF_KEY_ET_PASSWORD, "")

    fun init(context: Context) {
        prefs = context.getSharedPreferences(PREFES_FILENAME, Context.MODE_PRIVATE)
    }

    private inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor)->Unit) {
        val editor = edit()
        operation(editor)
        editor.apply()
    }

    var myIsLogin: Boolean
        get() = prefs.getBoolean(isLogin.first, isLogin.second)
        set(value) = prefs.edit{
            it.putBoolean(isLogin.first, value)
        }

    var myId:String
        get() = prefs.getString(mail.first, mail.second)?:""
        set(value) = prefs.edit {
            it.putString(mail.first, value)
        }

    var myEtPassword:String
        get() = prefs.getString(password.first, password.second)?:""
        set(value) = prefs.edit() {
            it.putString(password.first, value)
        }
}