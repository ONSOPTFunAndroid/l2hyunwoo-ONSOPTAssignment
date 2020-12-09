package com.example.onsoptfirstassignment.signup.viewmodel

import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.onsoptfirstassignment.preference.LoginPreference
import com.example.onsoptfirstassignment.signup.model.RequestSignUp
import com.example.onsoptfirstassignment.signup.repository.SignUpRepository
import kotlinx.coroutines.launch

class SignUpViewModel(private val signUpRepository: SignUpRepository) : ViewModel() {
    val registerName = ObservableField<String>()
    val registerId = ObservableField<String>()
    val registerPassword = ObservableField<String>()
    private val _isRegisterButtonClicked = MutableLiveData<Boolean>()
    val isRegisterButtonClicked: LiveData<Boolean>
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

    fun editTextBlankCheck(): Boolean = (registerName.get() != null)
            && (registerId.get() != null)
            && (registerPassword.get() != null)

    fun setUserInfoInSharedPreference() {
        LoginPreference.myIsLogin = false
        LoginPreference.myId = registerId.get() ?: "init"
        LoginPreference.myEtPassword = registerPassword.get() ?: "init"
    }
    fun sendDataToServer() {
         val userData = RequestSignUp(
             email = registerId.get()!!,
             userName = registerName.get()!!,
             password = registerPassword.get()!!
         )
         viewModelScope.launch {
             val data = signUpRepository.signUp(userData)
             require(data.success) { "Data 송/수신이 이뤄지지 않았습니다. ${data.success}" }
             Log.d("log user data", "$data")
         }

     }
}