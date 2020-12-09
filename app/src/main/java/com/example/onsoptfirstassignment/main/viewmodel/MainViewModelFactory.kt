package com.example.onsoptfirstassignment.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.onsoptfirstassignment.main.repository.SignInRepository

class MainViewModelFactory(private val signInRepository: SignInRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            MainViewModel(signInRepository) as T
        } else {
            throw IllegalArgumentException()
        }
    }
}