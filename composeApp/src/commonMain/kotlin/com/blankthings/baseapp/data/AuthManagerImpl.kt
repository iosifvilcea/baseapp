package com.blankthings.baseapp.data

class AuthManagerImpl: AuthManager {
    override suspend fun loginWithEmailAndPassword(email: String, password: String): LoginResult {
        return LoginResult.Success("accessToken", "uuid", true)
    }
}