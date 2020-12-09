package com.example.onsoptfirstassignment.main.model

import java.io.Serializable

data class ResponseSignIn(
    val data: Data,
    val message: String,
    val status: Int,
    val success: Boolean
) {
    data class Data(
        val email: String,
        val password: String,
        val userName: String
    ) : Serializable
}