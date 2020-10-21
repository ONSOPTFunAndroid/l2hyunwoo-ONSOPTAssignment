package com.example.onsoptfirstassignment.main.viewmodel

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.onsoptfirstassignment.preference.LoginPreference

class MainViewModel : ViewModel() {

    private val _userId = MutableLiveData<String>()
    val userId: LiveData<String>
        get() = _userId

    private val _userPassword = MutableLiveData<String>()
    val userPassword: LiveData<String>
        get() = _userPassword

    private val _isSignIn = MutableLiveData<Boolean>()
    val isSignIn: LiveData<Boolean>
        get() = _isSignIn

    private val _isRegisterClicked = MutableLiveData<Boolean>()
    val isRegisterClicked: LiveData<Boolean>
        get() = _isRegisterClicked

    val editId = ObservableField<String>()
    val editPassword = ObservableField<String>()

    init {
        _userId.value = INIT_VALUE
        _userPassword.value = INIT_VALUE
        _isSignIn.value = false
        _isRegisterClicked.value = false
    }

    fun isMatch(): Boolean =
        (_userId.value == editId.get() && _userPassword.value == editPassword.get())

    fun setIdAndPassword(id: String, password: String) {
        _userId.value = id
        _userPassword.value = password
    }

    fun setSignInFalse() {
        _isSignIn.value = false
    }

    fun setRegisterClickedFalse() {
        _isRegisterClicked.value = false
    }

    fun registerButtonClicked() {
        _isRegisterClicked.value = true
    }

    fun signInButtonClicked() {
        _isSignIn.value = true
    }

    fun registerValidation() : Boolean {
        return _userId.value != null
    }

    fun setAutoLoginInfo() {
        LoginPreference.myIsLogin = true
    }

    companion object {
        const val SIGN_UP_CODE = 1
        const val INIT_VALUE = "init"
    }
}