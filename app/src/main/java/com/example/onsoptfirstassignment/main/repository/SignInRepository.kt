package com.example.onsoptfirstassignment.main.repository

import com.example.onsoptfirstassignment.data.RetrofitService
import com.example.onsoptfirstassignment.main.model.RequestSignIn

class SignInRepository(
    private val retrofitService: RetrofitService
) {
    suspend fun signIn(requestSignIn: RequestSignIn) = retrofitService.postSignInUser(requestSignIn)
}