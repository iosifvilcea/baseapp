package com.blankthings.baseapp.data

interface AuthManager {
    suspend fun loginWithEmailAndPassword(email: String, password: String): LoginResult
}