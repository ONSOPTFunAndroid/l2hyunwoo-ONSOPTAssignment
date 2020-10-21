package com.example.onsoptfirstassignment.detail.viewmodel

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.example.onsoptfirstassignment.preference.LoginPreference

class WelcomeViewModel : ViewModel() {

    val welcomeText = ObservableField<String>()

    init {
        setText()
    }

    private fun setText() {
        val myId = LoginPreference.myId
        val setWelcomeText = myId + "님\n어서오세요"
        welcomeText.set(setWelcomeText)
    }
}