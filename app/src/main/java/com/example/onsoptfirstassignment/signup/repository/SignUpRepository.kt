package com.example.onsoptfirstassignment.signup.repository

import com.example.onsoptfirstassignment.data.RetrofitService
import com.example.onsoptfirstassignment.signup.model.RequestSignUp

class SignUpRepository(
    private val retrofitService: RetrofitService
) {
    suspend fun signUp(requestSignUp: RequestSignUp) = retrofitService.postSignUpUser(requestSignUp)
}