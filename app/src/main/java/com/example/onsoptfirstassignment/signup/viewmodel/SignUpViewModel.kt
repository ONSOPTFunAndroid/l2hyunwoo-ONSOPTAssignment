package com.example.onsoptfirstassignment.signup.viewmodel

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.onsoptfirstassignment.preference.LoginPreference

class SignUpViewModel : ViewModel() {
    val registerName = ObservableField<String>()
    val registerId = ObservableField<String>()
    val registerPassword = ObservableField<String>()
    private val _isRegisterButtonClicked = MutableLiveData<Boolean>()
    val isRegisterButtonClicked : LiveData<Boolean>
        get() = _isRegisterButtonClicked

    init {
        _isRegisterButtonClicked.value = false
    }

    fun isClicked() {
        _isRegisterButtonClicked.value = true
    }

    fun isButtonClickedSetFalse() {
        _isRegisterButtonClicked.value = false
    }

    fun editTextBlankCheck() : Boolean = (registerName.get() != null)
            && (registerId.get() != null)
            && (registerPassword.get() != null)

    fun setUserInfoInSharedPreference() {
        LoginPreference.myIsLogin = false
        LoginPreference.myId = registerId.get() ?: "init"
        LoginPreference.myEtPassword = registerPassword.get() ?: "init"
    }
}