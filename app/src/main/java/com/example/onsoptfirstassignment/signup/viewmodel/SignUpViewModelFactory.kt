package com.example.onsoptfirstassignment.signup.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.onsoptfirstassignment.signup.repository.SignUpRepository

class SignUpViewModelFactory(private val signUpRepository: SignUpRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(SignUpViewModel::class.java)) {
            SignUpViewModel(signUpRepository) as T
        } else {
            throw IllegalArgumentException()
        }
    }
}