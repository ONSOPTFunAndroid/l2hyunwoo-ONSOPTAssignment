package com.example.onsoptfirstassignment.data

import com.example.onsoptfirstassignment.main.model.RequestSignIn
import com.example.onsoptfirstassignment.main.model.ResponseSignIn
import com.example.onsoptfirstassignment.signup.model.RequestSignUp
import com.example.onsoptfirstassignment.signup.model.ResponseSignUp
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface RetrofitService {
    @Headers("Content-Type:application/json")
    @POST("users/signup")
    suspend fun postSignUpUser(
        @Body user: RequestSignUp
    ): ResponseSignUp

    @Headers("Content-Type:application/json")
    @POST("users/signin")
    suspend fun postSignInUser(
        @Body user: RequestSignIn
    ): ResponseSignIn
}