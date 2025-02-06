package com.blankthings.baseapp.data

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText

class AuthRepositoryImpl(val client: HttpClient): AuthRepository {
    override suspend fun loginWithEmailAndPassword(email: String, password: String): LoginResult {
        val text = client.get("/greeting").bodyAsText()
        println("Text-------> " + text)

        return if (email.contains("admin")) {
            LoginResult.Success("accessToken", "uuid", true)
        } else {
            LoginResult.Failed("", "Invalid credentials")
        }
    }
}