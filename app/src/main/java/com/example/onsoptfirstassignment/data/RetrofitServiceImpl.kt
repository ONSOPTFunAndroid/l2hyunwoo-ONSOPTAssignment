package com.example.onsoptfirstassignment.data

import com.example.onsoptfirstassignment.singleton.EncryptedKeyStore
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitServiceImpl {
    fun getSignUpInstance(): RetrofitService = Retrofit.Builder()
        .baseUrl(EncryptedKeyStore.getBaseUrl())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(RetrofitService::class.java)
}