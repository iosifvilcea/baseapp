package com.blankthings.baseapp.data

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText

class AuthRepositoryImpl(private val client: HttpClient): AuthRepository {
    override suspend fun loginWithEmailAndPassword(email: String, password: String): LoginResult {
        val text = client.get("/").bodyAsText()
        println("------------------")
        println("Text-------> " + text)
        println("------------------")

        return if (email.contains("admin")) {
            LoginResult.Success("accessToken", "uuid", true)
        } else {
            LoginResult.Failed("", "Invalid credentials")
        }
    }
}