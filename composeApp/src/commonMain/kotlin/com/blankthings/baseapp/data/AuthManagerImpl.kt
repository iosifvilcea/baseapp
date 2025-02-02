package com.blankthings.baseapp.data

class AuthManagerImpl: AuthManager {
    override suspend fun loginWithEmailAndPassword(email: String, password: String): LoginResult {
        return if (email == "admin") {
            LoginResult.Success("accessToken", "uuid", true)
        } else {
            LoginResult.Failed("", "Invalid credentials")
        }

    }
}