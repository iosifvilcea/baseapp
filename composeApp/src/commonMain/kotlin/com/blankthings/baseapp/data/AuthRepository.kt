package com.blankthings.baseapp.data

interface AuthRepository {
    suspend fun loginWithEmailAndPassword(email: String, password: String): LoginResult
}